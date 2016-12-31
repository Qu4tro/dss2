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
            set = prep.executeQuery();

            while (set.next()){
                Grupo grupo = new Grupo();
                grupo.setNome(set.getString(1));
                grupo.setModerador(set.getString(2));
                //despesas.getDespesas(utilizadores.getUtilizadores());
                grupos.put(grupo.getNome(), grupo);
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

        return grupos;
    }

    public boolean addGrupo(String nome, Utilizador moderador){
        Connection c = Connect.connect();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT `Grupo` VALUES (?, ?)"
            );

            prep.setString(1,nome);
            prep.setString(2,moderador.getID().toString());

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

    public boolean addUtilizadorToGrupo(Utilizador u, Grupo g){
        Connection c = Connect.connect();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT `GrupoUtilizador` VALUES (?, ?)"
            );

            prep.setString(1,u.getID().toString());
            prep.setString(2,g.getID().toString());

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
