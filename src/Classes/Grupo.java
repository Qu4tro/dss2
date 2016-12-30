/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import Classes.Utilizador;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Grupo{
    
    // Variaveis de Instância
    public Utilizador moderador;
    public List<Utilizador> membros;
    public String nome;
  //  public List<Despesa> historico;
       

    // Construtores
	public Grupo (Utilizador moderador, String nome){
		this.moderador = moderador;
		this.membros = new ArrayList<Utilizador>();
		this.nome = nome;
		//this.historico = new ArrayList<Despesa>();
	}
        public Grupo (String nome){
            this.moderador = null;
            this.membros = new ArrayList<Utilizador>();
            this.nome = nome;
        }
        public Grupo (){
            
        }

    // Metodos

	public boolean eliminarGrupo (){
		// todo DAO
		return false;
	}

	public String getNome (){
		return this.nome;
	}

	public Utilizador getModerador (){
		return this.moderador;
	}

	public void setNome (String nome){
		this.nome = nome;
	}
	
	public void setModerador (Utilizador moderador){
		this.moderador = moderador;
	}

	public boolean adicionarUtilizador (Utilizador user){
		boolean res = false;
		if (!this.membros.contains(user)){
			this.membros.add(user);
			res = true;
		}
		return res;
	}

	public boolean removerUtilizador (Utilizador user){
		boolean res = false;
		if (this.membros.contains(user)){
			this.membros.remove(user);
			res = true;
		}
		return res;
        }
        
        public void addMembro(Utilizador u){
            membros.add(u);
        }

	public ArrayList<Utilizador> listarMembros (){
		return new ArrayList<>(this.membros);
	}
        
        
/*	public ArrayList<Despesa> listarDespesas (){
		return new ArrayList<>(this.historico);
	}

       }*/
}