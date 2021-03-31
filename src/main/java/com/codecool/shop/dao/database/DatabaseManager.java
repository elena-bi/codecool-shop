package com.codecool.shop.dao.database;

import com.codecool.shop.dao.CartDaoJdbc;
import com.codecool.shop.util.ReadPropertyValues;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseManager {
    private ReadPropertyValues properties;
    private HashMap<String, String> config;

    public void setup(ReadPropertyValues propertyReader) throws SQLException, IOException {
        DataSource dataSource = connect();
        CartDaoJdbc cartDaoJdbc = new CartDaoJdbc(dataSource);
        this.properties = propertyReader;
        this.config = this.properties.getPropertyValues();
    }

    private DataSource connect() throws SQLException, IOException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = this.config.get("database");
        String user = this.config.get("user");
        String password = this.config.get("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

}
