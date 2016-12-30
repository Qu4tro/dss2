
package Classes;

import java.util.ArrayList;
import java.util.List;

 public class Utilizador {
     
     
    public String nick;
    public String avatar;
    public String iban;
    public String password;
    public String email;
    public List<Grupo> grupos;


 
    public Utilizador(String nick, String email) {
        this.nick = nick;
        this.email = email;
    }

    public Utilizador(String nickname,String email,String password,String iban){
        this.avatar = null;
        this.nick = nickname;
        this.password = password;
        this.email=email;
        this.iban = iban;
        this.grupos = new ArrayList<>();
    }

   

       
 
 
     // Metodos
 

        
	
    
	public String getNickname(){
		return this.nick;	
	}
    
	public void setNickname(String nickname){
		this.nick = nickname;
	}

     public String getAvatar() {
         return this.avatar;
     }

    public void setAvatar(String avatar){
		this.avatar = avatar;
	}

     public String getEmail() {
         return this.email;
     }

    public void setEmail(String email){
		this.email = email;
	}

     public String getPassword() {
         return this.password;
     }

    public void setPassword(String password){
		this.password = password;
	}

     public String getIBAN() {
         return this.iban;
     }

    public void setIBAN(String iban){
		this.iban = iban;
	}
    
    public List<Grupo> getGrupos(){
        return this.grupos;
        
    }
    public Grupo getGrupo1(){
        Grupo u = new Grupo();
        if(grupos!=null){
            u = grupos.get(0);
        }
        return u;
        
    }
    public void addGrupo(Grupo g){
        this.grupos.add(g);
    
    }
    public int getSize(){
        return grupos.size();
    }
 }