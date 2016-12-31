/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
class Connect {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:/home/quatro/dss/bd.db";
            //String url = "jdbc:sqlite:/home/bruno/Projetos/dss2/bd.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
}
