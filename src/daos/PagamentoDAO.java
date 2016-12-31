package daos;

import Classes.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by quatro on 30/12/16.
 */
public class PagamentoDAO {


    public static Map<Integer, Pagamento> getPagamentos(Map<String, Utilizador> users){
        Connection c = Connect.connect();
        ResultSet set = null;
        Map<Integer, Pagamento> pagamentos = new HashMap<>();
        Map<Integer, String> idUsers = users.entrySet()
                                            .stream()
                                            .collect(Collectors.toMap(e -> e.getValue().getID(),
                                                                      e -> e.getKey()));

            Pagamento p;
            try {
                PreparedStatement prep = c.prepareStatement(
                        "SELECT rowid, * FROM Pagamento",
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY
                );
                set = prep.executeQuery();

                while (set.next()) {
                    p = new Pagamento();
                    p.setID(set.getInt(1));
                    p.setValor(set.getInt(2));
                    p.setModo("");
                    p.setCredor(idUsers.get(set.getInt(4)));
                    p.setDevedor(idUsers.get(set.getString(5)));

                    pagamentos.put(p.getID(), p);
                }

            } catch(SQLException e){
                System.out.println(e.getMessage());
            } finally{
                try {
                    set.close();
                    c.close();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }

        return pagamentos;
    }

    public static boolean addPagamento(Pagamento p, Map<String, Utilizador> users){

        Map<String, Integer> idUsers = users.entrySet()
                                            .stream()
                                            .collect(Collectors.toMap(e -> e.getKey(),
                                                                      e -> e.getValue().getID()));

        Connection c = Connect.connect();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT INTO `Pagamento` VALUES (?, ?, ?, ?, ?)"
            );

            prep.setString(1, Utils.dataAgora());
            prep.setString(2, p.getValor().toString());
            prep.setString(3,"");
            prep.setString(4,idUsers.get(p.getCredor()).toString());
            prep.setString(5,idUsers.get(p.getDevedor()).toString());

            prep.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        } finally{
            try {
                c.close();
            } catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }
}
