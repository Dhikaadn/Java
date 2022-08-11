/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 *
 * @author D2A
 */
public class MyConnection {
    public static Connection con;
    public static Statement stm;
    /*
    public static void main(String []ar){
        try{
            String url = "jdbc:mysql://localhost/dbjava";
            String user = "root";
            String pass = "";
            con = (Connection) DriverManager.getConnection(url, user, pass);
            stm =  (Statement) con.createStatement();
            System.out.println("Koneksi berhasil");
            
            
        }catch(Exception e){
            System.err.println("Koneksi Gagal: "  + e.getMessage());
        }
    }
*/
    
}
