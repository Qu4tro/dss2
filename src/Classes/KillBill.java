/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import daos.GrupoDAO;
import daos.UtilizadorDAO;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class KillBill {
    
    
    public Map<String,Utilizador> users;
    public Map<String,Grupo> grupos;
    public Utilizador loggedUser;
    
    public KillBill(){
        this.users = new HashMap<>();
        this.grupos = new HashMap<>();
        Utilizador u = new Utilizador("manuel","","12345","");
        Grupo b = new Grupo ("Principal");
        loggedUser = u;
        b.addMembro(u);
        this.users.put("manuel",u);
        this.grupos.put("Principal", b);
    }

  /*  public int getIntLogin(String email, String pass) throws SQLException {
    
        int abc = NewClass.verificaLogin(email,pass);
        return abc;
    }*/

    public Map<String, Utilizador> getUsers() {
        return UtilizadorDAO.getUtilizadores();
    }
    
    
    public boolean registarUtilizadorNoDB (String nickname, String email, String password, String iban){

        if (UtilizadorDAO.existeUtilizador(nickname)){
            return false;
        } else {
            Utilizador u = new Utilizador(nickname,email,password,iban);
            this.users.put(nickname, u);
            UtilizadorDAO.addUtilizador(u);

            return true;
        }
    }

    public boolean getIntLoginNoDB(String nome, String password){

        loggedUser =
        Optional.ofNullable(
        Optional.ofNullable(users.get(nome))
                    .orElse(UtilizadorDAO.getUtilizador(nome)))
                    .filter(u -> u.getPassword().equals(password))
                    .orElse(null);

        return (loggedUser != null);
    }
    
    public List<String> getGrupos(String user){
        //    return users.get(user).getGrupos().stream().map(Grupo::getNome).collect(Collectors.toList());
        return GrupoDAO.getGrupos(user).keySet()
                                       .stream()
                                       .collect(Collectors.toList());
    }
    
    public Grupo getGrupo(String nome){
            return this.grupos.get(nome);
    }

    public void adicionarDespesa(String grupo, Utilizador responsavel, String descricao, Float valor,GregorianCalendar dataDespesa) {

        GregorianCalendar now = new GregorianCalendar();
        Optional.ofNullable(grupos.get(grupo)).ifPresent(g ->
                g.adicionarDespesa(new Despesa(descricao, valor, responsavel.getNickname(), now, dataDespesa)));
    }

       
    public boolean adicionarGrupo(String nome){
        boolean res = false;
        if (!this.grupos.containsKey(nome)){
            this.grupos.put(nome,new Grupo(this.loggedUser.getNickname(),nome));
            res = true;
        }
        return res;
    }
}
