package com.likelion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = "jdbc:mysql://127.0.0.1:3306/likelion";
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        return c;
    }
}
