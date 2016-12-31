package daos;

import Classes.Despesa;
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

public class GrupoDAO {
    
    public static Map<String, Grupo> getGrupos(String user){

        Map<String, Grupo> grupos = new HashMap<>();
        Connection c = Connect.connect();
        ResultSet set = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT rowid, * FROM Grupo"
            );
            set = prep.executeQuery();

            while (set.next()){
                Grupo grupo = new Grupo();
                grupo.setID(set.getInt(1));
                grupo.setNome(set.getString(2));
                grupo.setModerador(set.getString(3));
                getListaMembros(grupo.getID()).stream().forEach(membro -> grupo.addMembro(membro));
                getHistórico(grupo.getID()).stream().forEach(despesa -> grupo.adicionarDespesa(despesa));
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

    public static boolean addGrupo(String nome, Utilizador moderador){
        Connection c = Connect.connect();
        try {
            PreparedStatement prep = c.prepareStatement(
                    "INSERT OR REPLACE INTO `Grupo` VALUES (?, ?)"
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

    public static boolean addUtilizadorToGrupo(Utilizador u, Grupo g){
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
    
    
    public static List<Utilizador> getListaMembros (Integer id){
        ArrayList<Utilizador> membros = new ArrayList<>();
        Connection c = Connect.connect();
        ResultSet set = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT * FROM GrupoUtilizador where GrupoUtilizador.Grupo = ?"
            );
            
            prep.setString(1,id.toString());
            
            set = prep.executeQuery();

            while (set.next()){
                Utilizador user = UtilizadorDAO.getUtilizador(set.getInt(1));
                membros.add(user);
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

        return membros;
    }
    
    public static List<Despesa> getHistórico (Integer id){
        ArrayList<Despesa> historico = new ArrayList<>();
        Connection c = Connect.connect();
        ResultSet set = null;

        try {
            PreparedStatement prep = c.prepareStatement(
                    "SELECT * FROM Despesa where Despesa.Grupo = ?"
            );
            
            prep.setString(1,id.toString());
            
            set = prep.executeQuery();

            while (set.next()){
                Despesa d = DespesaDAO.getDespesa(set.getInt(1));
                historico.add(d);
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

        return historico;
    }
}
