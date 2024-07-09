/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 *
 * @author Lenovo
 */
public class DBContext {
    private String hostName;
    private String instance;
    private String port;
    private String dbName;
    private String userName;
    private String password;

    public DBContext() {
        this.hostName = "localhost";
        this.instance = "DESKTOP-9AQQ2P7\\SQLEXPRESS";// Ten cua Server - Dung khi may co nhieu hon 2 Server
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.userName = "admin";
        this.password = "12345678";
    }
    
    public DBContext(ServletContext sc) {
        this.hostName = sc.getInitParameter("hostName");
        this.instance = sc.getInitParameter("instance");// Ten cua Server - Dung khi may co nhieu hon 2 Server
        this.port = sc.getInitParameter("port");
        this.dbName = sc.getInitParameter("dbName");
        this.userName = sc.getInitParameter("userName");
        this.password = sc.getInitParameter("passWord");
    }
    
    
    //Chuoi Ket Noi Goc: 
    //"jdbc:sqlserver://<hostName>\<instance>:<port>;databaseName=<dbName>;user=<username>;password=<pass>;"
    private String urlString(){
        return String.format("jdbc:sqlserver://%s%s:%s;databasename=%s;user=%s;password=%s;",
                this.hostName, this.instance.length()>0?"\\"+this.instance:"", this.port, this.dbName, this.userName, this.password);
    }
    
    /**
     * This method uses to get a connection to SQL to Database Server
     * @return  
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection connect = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connect = DriverManager.getConnection(urlString());
        return connect;
    }
    
}
