package ru.itis.auction.utils;

import ru.itis.auction.utils.exceptions.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private final String HOST = "jdbc:postgresql://localhost:5432/AuctionDB";
    private final String USER = "postgres";
    private final String PASS = "1234";
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection con;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
