package daos;

import Classes.Utilizador;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by quatro on 30/12/16.
 */
public class UtilizadorDAO {
    

    private static Utilizador rowToUtilizador(ResultSet set) throws SQLException {
        Utilizador user = new Utilizador();
        user.setID(set.getInt(1));
        user.setNickname(set.getString(2));
        user.setAvatar(set.getString(4));
        user.setEmail(set.getString(4));
        user.setPassword(set.getString(5));
        user.setIBAN(set.getString(6));

        return user;
    }

    public static Map<String, Utilizador> getUtilizadores(){
        Connection c = Connect.connect();
        ResultSet set = null;
        Utilizador user;

        Map<String, Utilizador> users = new HashMap<>();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Utilizador",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            set = prep.executeQuery();

            while (set.next()) {
                user = rowToUtilizador(set);
                users.put(user.getNickname(), user);
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

        return users;
    }

    public static boolean existeUtilizador(String nome){
        return getUtilizador(nome) != null;
    }
    public static Utilizador getUtilizador(String nome){

        boolean in;
        Connection c = Connect.connect();
        ResultSet set = null;
        Utilizador user = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Utilizador WHERE Nickname = ?",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );

            prep.setString(1,nome);
            set = prep.executeQuery();

            in = set.next();
            if (in){
                user = rowToUtilizador(set);
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

    public static Utilizador getUtilizador(Integer id){

        boolean in;
        Connection c = Connect.connect();
        ResultSet set = null;
        Utilizador user = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Utilizador WHERE rowid = ?",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );

            prep.setString(1,id.toString());
            set = prep.executeQuery();

            in = set.next();
            if (in){
                user = rowToUtilizador(set);
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

        return user;
    }

    public static void addUtilizador(Utilizador utilizador){

        Connection c = Connect.connect();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT OR REPLACE INTO `Utilizador` VALUES (?, ?, ?, ?, ?)"
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
