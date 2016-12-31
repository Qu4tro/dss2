
package Classes;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

 public class Utilizador {

     private int id;
     private String nick;
     private String avatar;
     private String iban;
     private String password;
     private String email;

    public Utilizador(){
    }
 
    public Utilizador(String nick, String email) {
        this.nick = nick;
        this.email = email;
    }

    public Utilizador(String nickname,String email,String password,String iban){
        this.avatar = "";
        this.nick = nickname;
        this.password = password;
        this.email=email;
        this.iban = iban;
    }


     // Metodos

     public int getID(){
        return id;
     }

     public void setID(int id){
         this.id = id;
     }
    
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

 }