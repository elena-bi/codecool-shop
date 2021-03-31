package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;

public class CartDaoJdbc implements CartDao{
    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setCartMap(HashMap<Integer, Integer> cartMap) {
        for (int value : cartMap.keySet()) {
            try (Connection conn = dataSource.getConnection()) {
                String sql = "INSERT INTO cart (product_id, product_amount) VALUES (?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, value);
                statement.setInt(2, cartMap.get(value));
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public HashMap<Integer, Integer> getCartMap() {
        return null;
    }
}
