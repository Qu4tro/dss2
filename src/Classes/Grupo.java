/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


import Classes.Utilizador;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Grupo{
    
    // Variaveis de Instância
    private Integer id;
    private String moderador;
    private List<Utilizador> membros;
    private String nome ;
    private List<Despesa> historico;
       

    // Construtores
	public Grupo (String moderador, String nome){
		this.moderador = moderador;
		this.membros = new ArrayList<>();
		this.nome = nome;
		this.historico = new ArrayList<>();
	}
        
        public Grupo (String nome){
            this.moderador = null;
            this.membros = new ArrayList<>();
            this.nome = nome;
            this.historico = new ArrayList<>();

        }
        public Grupo (){
            this.historico = new ArrayList<>();
            this.membros = new ArrayList<>();
        }

    // Metodos


	public Integer getID(){
	    return id;
    }

    public void setID(Integer id){
	    this.id = id;
    }

	public String getNome (){
		return this.nome;
	}

	public String getModerador (){
		return this.moderador;
	}

	public void setNome (String nome){
		this.nome = nome;
	}

	public void setModerador (String moderador){
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
        
        
        public void adicionarDespesa(Despesa d) {
            historico.add(d);
        }
        
        
        public List<Despesa> listarDespesas (){
		    return this.historico;
	    }

}
