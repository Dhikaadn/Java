/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class MyConnection {
    public static Connection con;
    public static Statement stm;
    public static void main(String args[]){
        try{
            String url = "jdbc:mysql://localhost/dbbelanja";
            String user = "root";
            String pass = "dhika2512";
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("Koneksi berhasil;");
        } catch (Exception e){
            System.out.println("Koneksi gagal"+e.getMessage());
        }
    }
}
