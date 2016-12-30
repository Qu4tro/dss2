package daos;

import Classes.Utilizador;

import java.sql.*;

/**
 * Created by quatro on 30/12/16.
 */
public class UtilizadorDAO {

    public static boolean existeUtilizador(String nome){
        return getUtilizador(nome) != null;
    }
    public static Utilizador getUtilizador(String nome){

        boolean in = false;
        Connection c = Connect.connect();
        ResultSet set = null;
        Utilizador user = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT * FROM Utilizador WHERE Nickname = ?",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );

            prep.setString(1,nome);
            set = prep.executeQuery();

            in = set.next();

            if (in){
                user = new Utilizador();
                user.setNickname(set.getString(1));
                user.setAvatar(set.getString(2));
                user.setEmail(set.getString(3));
                user.setPassword(set.getString(4));
                user.setIBAN(set.getString(5));
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

        return user; }

    public static void addUtilizador(Utilizador utilizador){

        Connection c = Connect.connect();

        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT INTO `Utilizador` VALUES (?, ?, ?, ?, ?)"
            );

            prep.setString(1,utilizador.getNickname());
            prep.setString(2,utilizador.getAvatar());
            prep.setString(3,utilizador.getEmail());
            prep.setString(4,utilizador.getPassword());
            prep.setString(5,utilizador.getIBAN());

            prep.executeUpdate();

        } catch(SQLException e){
                System.out.println(e.getMessage());
        } finally{
            try {
                c.close();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}
