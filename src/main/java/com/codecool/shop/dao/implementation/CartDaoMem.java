package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;

import java.util.HashMap;

public class CartDaoMem implements CartDao {
    private HashMap<Integer, Integer> cartMap;
    private static CartDaoMem instance = null;

    private CartDaoMem() {}

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void setCartMap(HashMap<Integer, Integer> cartMap) {
        this.cartMap = cartMap;
    }

    @Override
    public HashMap<Integer, Integer> getCartMap(){
        return this.cartMap;
    }

}
