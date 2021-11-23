package com.ifour.employeeservice;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionPoolClass {
    final int max = 5;
    final String user = "root";
    final String password = "MainWill";
    List<Connection> availConnection = new ArrayList<>();
    List<Connection> usedConnection = new ArrayList<>();

    private Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","MainWill72");
    }

    public Connection getConnection() throws SQLException {
        for (int count = 0; count < max; count++) {
            if (availConnection.size() == 0) {
                System.out.println("Sorry all Connections are in use...!");
                usedConnection.add(this.createNewConnection());
            }
        }
        return this.createNewConnection();
    }

    public void releaseConnection() throws SQLException{
        this.createNewConnection().close();
        usedConnection.remove(this.createNewConnection());
        availConnection.add(this.createNewConnection());
    }

    public int usedCount(){
        return usedConnection.size();
    }

    public int availCount(){
        return availConnection.size();
    }
}