package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewClass {
	
	

	public static int verificaLogin(String nome, String pass){
		int flag = 0;
		Connection c = Connect.connect();
		ResultSet set = null;
		try{
			PreparedStatement prep = c.prepareStatement("SELECT * FROM Utilizador WHERE Nickname = ? AND PASSWORD = ?");
			prep.setString(1,nome);
			prep.setString(2,pass);
			set = prep.executeQuery();
			if(set.next()){
				flag = 1;

			}
			

		}
		catch(SQLException e){
			System.out.println("erro");
		}
		finally{
                    try{
                    set.close();
                    c.close();
                  }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
		return flag;
	}



}