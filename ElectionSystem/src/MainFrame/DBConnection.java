/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;
/**
 *
 * @author Mokdad
 */
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;


public class DBConnection {
 // static String url= "jdbc:sqlite:D:\\Courses\\Java\\JH\\lab 6\\employee.sqlite";
 // static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";

   // static String user = "SYSTEM";
   // static String pass = "pass";
    
    
    
    static String url = "jdbc:sqlserver://MOSTAFA-HUSSEIN\\MMSQLSERVER;databaseName=Election_System;encrypt=false";
    static   String username = "sa";
    static  String password = "root";
    
    public static Connection getConnection() {
       //Connection conn = null;
        Connection connection = null;
        try {
             connection = DriverManager.getConnection(url, username, password);

            //Class.forName("oracle.jdbc.driver.OracleDriver"); //oracle
           // conn = DriverManager.getConnection(url, user, pass); //oracle
            //conn = DriverManager.getConnection(url); //sqlite
            System.out.println("Connection to database has been established.");
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
       // } catch (ClassNotFoundException ex) {
        //    ex.printStackTrace(); 
        //    System.out.println("Oracle driver not found!");
       // } catch (SQLException sqlEx) {
        //    System.out.println(sqlEx);
        //}        
        return connection;
    }
}
