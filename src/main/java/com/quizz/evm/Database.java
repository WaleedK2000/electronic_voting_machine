package com.quizz.evm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection mySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap", "root", "waleed123");
            System.out.println("Connected");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Not loaded");
        } catch (SQLException e) {
            System.out.println("Connection Fail");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unknown Error");
        }
        return null;
    }










}
