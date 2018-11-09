package ru.itis.neo4j.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector implements AutoCloseable {
    private final static String URL = "jdbc:neo4j:bolt://localhost";

    private java.sql.Connection connection;

    public Connector(String username, String password) {
        try {
            this.connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
