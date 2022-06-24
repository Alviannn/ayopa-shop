package com.github.argajuvi.database;

import java.sql.*;

import com.sun.corba.se.spi.orbutil.fsm.State;

public class Database {

    private static Database instance;

    private final String host, dbName, username, password;
    private final int port;

    private Connection conn;

    /**
     * <h1>Singleton Design Pattern</h1>
     *
     * <h2>Why we use it?</h2>
     * 1. We think it is the most easiest and the simplest design pattern to be implemented.<br>
     * 2. It is easy to understand.<br>
     * 3. Singleton is a Creational Design Pattern and we have that exact object creation case.<br>
     * 4. We have to create database instance, which is one of the common example from Singleton.<br><br>
     *
     * <h2>Explanation</h2>
     * According to this <a href="https://refactoring.guru/design-patterns/singleton">source</a>, Singleton
     * is a pattern that will only let us create no more than one instance. Since our project includes the
     * creation of database, of course we won't create multiple databases or else our program will be run into
     * confusion as to which data source should be used; and there is no point for creating multiple databases.
     * As for the implementation, the concept is <b>the instance will be created if there is no existing instance</b>.
     * To accommodate this concept, the function for object creation need to be set as {@code public static} so
     * it can be accessed globally while the instance in question could remain {@code private}.<br><br>
     *
     * <h2>Purpose</h2>
     * The only purpose of this function is <b>to get the Database instance</b>, with condition it
     * will be created if it's not exists and will be returned the existing instance if it exists.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private Database() {
        this.host = "localhost";
        this.port = 3306;
        this.dbName = "ayopa_shop";
        this.username = "root";
        this.password = "";
    }

    public void connect() throws SQLException {
        String urlFormat = "jdbc:mysql://%s:%d/%s";
        String connUrl = String.format(urlFormat, host, port, dbName);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find MySQL driver");
        }

        this.conn = DriverManager.getConnection(connUrl, username, password);
    }

    public void disconnect() throws SQLException {
        if (conn == null) {
            return;
        }

        conn.close();
    }

    public ResultSet getResults(String sql, Object ...args) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }

        return statement.executeQuery();
    }

    public void execute(String sql, Object ...args) {
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
