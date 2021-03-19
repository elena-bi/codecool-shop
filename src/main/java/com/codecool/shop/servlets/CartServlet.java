package com.codecool.shop.servlets;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, Integer> cartMap = new HashMap<>();

        BufferedReader reader = req.getReader();
        String message = reader.lines().collect(Collectors.joining(","));
        int[] arr = Stream.of(message.split(",")).mapToInt(Integer::parseInt).toArray();
        int j = 0;
        for (int i = 0; i < arr.length - 1; i+=2) {
            cartMap.put(arr[i], arr[++j]);
            j++;
        }

        CartDao shoppingCart = CartDaoMem.getInstance();
        shoppingCart.setCartMap(cartMap);
    }
}


