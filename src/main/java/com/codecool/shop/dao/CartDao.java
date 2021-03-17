package com.codecool.shop.dao;

import java.util.HashMap;

public interface CartDao {
    void setCartMap(HashMap<Integer, Integer> cartMap);
    HashMap<Integer, Integer> getCartMap();
}
