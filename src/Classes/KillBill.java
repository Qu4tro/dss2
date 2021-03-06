/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import daos.DespesaDAO;
import daos.GrupoDAO;
import daos.UtilizadorDAO;
import sun.security.krb5.internal.crypto.Des;

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
        Utilizador u = new Utilizador("manuel","Teste","12345","teeeste");
        Grupo b = new Grupo ("Principal");
        loggedUser = u;
        b.addMembro(u);
        this.users.put("manuel",u);
        this.grupos.put("Principal", b);
    }


    public Map<String, Utilizador> getUsers() {
        return UtilizadorDAO.getUtilizadores();
    }
    
    public String getLog(){
        return this.loggedUser.getNickname();
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

        // ADD cenas da base de dados referentes ao utilizador para o grupos e users.
        
        if (loggedUser != null){
            users.putAll(UtilizadorDAO.getUtilizadores());
            grupos.putAll(GrupoDAO.getGrupos(loggedUser.getNickname()));
        }
                
       
        return (loggedUser != null);
    }
    
    public List<String> getGrupos(String user){
        //    return users.get(user).getGrupos().stream().map(Grupo::getNome).collect(Collectors.toList());
        Map<String, Grupo> grupos = GrupoDAO.getGrupos(user);
        if(grupos == null)
            System.out.println("Teste");
        this.grupos = grupos;
        return grupos.keySet()
                     .stream()
                     .collect(Collectors.toList());
    }
    
    public Grupo getGrupo(String nome){
            return this.grupos.get(nome);
    }

    public void adicionarDespesa(String grupo, Utilizador responsavel, String descricao, Float valor,GregorianCalendar dataDespesa) {

        GregorianCalendar now = new GregorianCalendar();
        Despesa d = new Despesa(descricao, valor, responsavel.getNickname(), now, dataDespesa);
        Optional.ofNullable(grupos.get(grupo)).ifPresent(g ->
                g.adicionarDespesa(d));
        d.setGrupo(grupos.get(grupo).getID());

        DespesaDAO.addDespesa(d);
    }

    public boolean adicionarGrupo(String nome){
        if (!this.grupos.containsKey(nome)){
            GrupoDAO.addGrupo(nome, this.loggedUser);
            this.grupos.put(nome,new Grupo(this.loggedUser.getNickname(),nome));
            return true;
        }
        return false;
    }
   
}
