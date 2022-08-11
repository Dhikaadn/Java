/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;
import java.sql.*;

/**
 *
 * @author ASUS M409D
 */
public class KoneksiMysql {
    String url, usr, pwd, dbn;
public KoneksiMysql (String dbn) {
    this.url = "jdbc:mysql://localhost/" + dbn;
    this.usr = "root";
    this.pwd = "";
}
public KoneksiMysql (String host, String user, String pass,
String dbn) {
    this.url = "jdbc:mysql://" + host + "/" + dbn;
    this.usr = user;
    this.pwd = pass;
}
public Connection getConnection() {
    Connection con = null;
    try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(this.url, this.usr,
this.pwd);
    } catch (ClassNotFoundException e) {
    System.out.println ("Error #1 : " + e.getMessage());
    System.exit(0);
    } catch (SQLException e) {
    System.out.println ("Error #2 : " + e.getMessage());
    System.exit(0);
    }
    return con;
}
    /*
    public static void main (String args[]) {
    KoneksiMysql kon = new KoneksiMysql ("barang");
    Connection c = kon.getConnection();
    }
        */
}
