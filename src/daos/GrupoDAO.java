package daos;

import Classes.Grupo;
import Classes.Utilizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by quatro on 30/12/16.
 */
public class GrupoDAO {

    public static List<String> getGrupos(String user){

        List<String> nomesGrupos = new ArrayList<>();

        Connection c = Connect.connect();
        ResultSet set = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT * FROM GrupoUtilizador WHERE Utilizador = ?"
            );

            prep.setString(1,user);
            set = prep.executeQuery();

            while (set.next()){
                nomesGrupos.add(set.getString(1));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
            try {
                set.close();
                c.close();
            } catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }

        return nomesGrupos;
    }
}
