/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KillBill {
    
    
    public Map<String,Utilizador> users;
    public Map<String,Grupo> grupos;
    public Utilizador loggedUser;
    
    public KillBill(){
        this.users = new HashMap<>();
        this.grupos = new HashMap<>();
        Grupo b = new Grupo ( "Principal");
        Utilizador u = new Utilizador("manuel","","12345","");
        loggedUser = u;
        u.addGrupo(b);
        b.addMembro(u);
        this.users.put("manuel",u);
        this.grupos.put("Principal", b);
    }

  /*  public int getIntLogin(String email, String pass) throws SQLException {
    
        int abc = NewClass.verificaLogin(email,pass);
        return abc;
    }*/

    public Map<String, Utilizador> getUsers() {
        return users;
    }
    
    
    public boolean registarUtilizadorNoDB (String nickname, String email, String password, String iban){
        Utilizador u = new Utilizador(nickname,email,password,iban);
        boolean res = false;
        if (!this.users.containsKey(nickname)){
            this.users.put(nickname,u);
            res = true;
        }
        return res;
    }
    
    public int getIntLoginNoDB(String nome, String password){
        if(this.users.containsKey(nome)){
            String s = this.users.get(nome).getPassword();
            if(s.equals(password)){
                return 1;
            }
            else return 0;
        }
        else return 0;
    }
    
    public List<String> getGrupos(String nome){
            List<String> lista = new ArrayList();
            for(Grupo u : users.get(nome).getGrupos()){
                lista.add(u.getNome());
            }
            return lista;
            
        //isto e a lista para depois se meter na Jlist
    }
    
    public Grupo getGrupo(String nome){
            return this.grupos.get(nome);
    }

    public int getSizeGrupo(String nome){
      int size = this.users.get(nome).getSize();
      return size;
    }
}
