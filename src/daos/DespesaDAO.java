package daos;

import Classes.Despesa;
import Classes.Utilizador;
import Classes.Utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DespesaDAO {

    public static Despesa getDespesa(Integer id){
        Connection c = Connect.connect();
        ResultSet set = null;
        boolean in;

        Despesa d = new Despesa();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Despesa",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            set = prep.executeQuery();

            in = set.next();
            if (in){
                d.setID(set.getInt(1));
                d.setDescricao(set.getString(2));
                d.setValor(set.getFloat(3));
                d.setResponsavel(UtilizadorDAO.getUtilizador(set.getInt(4)).getNickname());
                d.setDataCriacao(set.getString(5));
                d.setDataDespesa(Utils.parseData(set.getString(6)));
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

        return d;
    }

    public static Map<Integer, Despesa> getDespesas(Map<String, Utilizador> users){
        Connection c = Connect.connect();
        ResultSet set = null;

        Map<Integer, Despesa> despesas = new HashMap<>();
        Map<Integer, String> idUsers = users.entrySet()
                                            .stream()
                                            .collect(Collectors.toMap(e -> e.getValue().getID(),
                                                                      e -> e.getKey()));

        Despesa d;
        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Despesa",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            set = prep.executeQuery();

            while (set.next()) {
                d = new Despesa();
                d.setID(set.getInt(1));
                d.setDescricao(set.getString(2));
                d.setValor(set.getFloat(3));
                d.setResponsavel(idUsers.get(set.getInt(4)));
                d.setDataCriacao(set.getString(5));
                d.setDataDespesa(Utils.parseData(set.getString(6)));


                despesas.put(d.getID(), d);
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

        return despesas;
    }



    public static boolean addDespesa(Despesa despesa){
        Connection c = Connect.connect();

        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT INTO `Despesa` VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            prep.setString(1,despesa.getDescricao());
            prep.setString(2,despesa.getValor().toString());
            prep.setString(3,despesa.getResponsavel());
            prep.setString(4,Utils.formatGreg(despesa.getDataCriacao()));
            prep.setString(5,despesa.getDataCriacao().toString());
            prep.setString(6,despesa.getTempoDeRecurrencia().toString());
            prep.setString(7,despesa.getGrupo().toString());

            prep.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        } finally{
            try {
                c.close();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return true;
    }


}
