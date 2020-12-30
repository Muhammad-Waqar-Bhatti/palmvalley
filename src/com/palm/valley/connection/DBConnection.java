/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palm.valley.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public class DBConnection {
    private static Connection con = null;
    private DBConnection(){}
    
    public static Connection getConnection(){
        try{
            if(con==null) {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palm_valley_db", "root","");			
            }
        }catch(Exception e){
        
        }
        return con;
    
    }
}
