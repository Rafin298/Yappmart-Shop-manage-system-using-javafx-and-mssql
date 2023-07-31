/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Orkid
 */
public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        String url = "jdbc:sqlserver://DESKTOP-CPF9071\\SQLEXPRESS:1433;databaseName=Yapp";
        String user = "sa";
        String password = "1234";

        try {
//        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", user, password);
            databaseLink = DriverManager.getConnection(url, user, password);
            //System.out.println("yooodfgooooo");
        } catch (SQLException e) {
            //System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        return databaseLink;
    }

}
