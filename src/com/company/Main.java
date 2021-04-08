package com.company;

import java.io.IOException;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;

public class Main {

    static final String DB = "jdbc:postgresql://localhost:5432/vocabulary";
    static final String USER = "postgres";
    static final String PASS = "0000";

    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) throws IOException {


        try{
            Class.forName("org.postgresql.Driver");
            //conn = DriverManager.getConnection(DB, USER, PASS);
//            connect();
//            stmt = conn.createStatement();
//            ResultSet resultSet;

//            String sql = "SELECT * FROM words";
//            ResultSet resultSet = stmt.executeQuery(sql);

//            while (resultSet.next()) {
//                String word = resultSet.getString("word");
//                String description = resultSet.getString("description");
//
//                books.add(new Book(word, description, false));
//            }

//            resultSet = zapros("ручка");

//            resultSet.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        Window win = new Window();
        win.pack();
        win.setSize(800,333);
        win.setVisible(true);
        System.exit(0);
    }

    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(DB, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static String request(String s) {
        String sql = "SELECT * FROM words WHERE word = '" + s + "';";
        ResultSet resultSet = null;
        String res = "";
        try {
            connect();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String word = resultSet.getString("word");
                String description = resultSet.getString("description");
                res = res + String.format("%s - %s", word, description + "\r\n");
            }
            resultSet.close();
        } catch (SQLException se){
            se.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return res;
    }

    public static void insert(String word, String description) throws SQLException{
        String sql = "INSERT INTO words VALUES('" + word + "','" + description + "');";
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException se){
            se.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
