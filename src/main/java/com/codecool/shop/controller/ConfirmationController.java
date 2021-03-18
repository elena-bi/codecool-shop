package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDetailsDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, Integer> cart = CartDaoMem.getInstance().getCartMap();
        UserDetailsDao userDetails = UserDetailsDaoMem.getInstance();
        HashMap<String, HashMap<String, String>> items = new HashMap<>();
        float priceSum = 0;

        for (Map.Entry<Integer, Integer> entry: cart.entrySet()) {
            String name = ProductDaoMem.getInstance().find(entry.getKey()).getName();
            HashMap<String, String> values = new HashMap<>();
            values.put("amount", "x" + entry.getValue());
            values.put("price", (entry.getValue() * ProductDaoMem.getInstance().find(entry.getKey()).getDefaultPrice()) + "$");
            items.put(name, values);
            priceSum += ProductDaoMem.getInstance().find(entry.getKey()).getDefaultPrice() * entry.getValue();
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("items", items);
        context.setVariable("userDetails", userDetails);
        context.setVariable("totalPrice", priceSum);
        engine.process("confirmation/confirmation.html", context, resp.getWriter());
    }
}
