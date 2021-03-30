package com.codecool.shop.dao.database;

import com.codecool.shop.util.ReadPropertyValues;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseManager {

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
    }

    private DataSource connect() throws SQLException, IOException {
        ReadPropertyValues properties = new ReadPropertyValues();
        HashMap<String, String> config = properties.getPropertyValues();

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = config.get("database");
        String user = config.get("user");
        String password = System.getenv("MY_PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

}
