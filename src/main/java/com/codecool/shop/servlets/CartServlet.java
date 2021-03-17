package com.codecool.shop.servlets;


import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.google.gson.JsonArray;

import javax.script.ScriptContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.codecool.hackernews.JsonHandler;

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
        for (int i = 0; i < message.length() - 2; i+=4) {
            cartMap.put((int) message.charAt(i) - 48, (int) message.charAt(i + 2) - 48);
        }
        CartDao shoppingCart = CartDaoMem.getInstance();
        shoppingCart.setCartMap(cartMap);
    }
}
