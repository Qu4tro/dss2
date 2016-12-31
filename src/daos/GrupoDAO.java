package daos;

import Classes.Grupo;
import Classes.Utilizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by quatro on 30/12/16.
 */
public class GrupoDAO {

    public static Map<String, Grupo> getGrupos(String user){

        Map<String, Grupo> grupos = new HashMap<>();
        Connection c = Connect.connect();
        ResultSet set = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT * FROM Grupo"
            );

            prep.setString(1,user);
            set = prep.executeQuery();

            while (set.next()){
                Grupo grupo = new Grupo();
                grupo.setNome(set.getString(2));
                grupo.setModerador(set.getString(3));
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

        return grupos;
    }
}
