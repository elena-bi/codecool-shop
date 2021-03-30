package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.util.HashMap;

public class CartDaoJdbc implements CartDao{
    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setCartMap(HashMap<Integer, Integer> cartMap) {

    }

    @Override
    public HashMap<Integer, Integer> getCartMap() {
        return null;
    }
}
