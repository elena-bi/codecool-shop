package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
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

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    // Temporary cart items
    // TODO: Replace when cart DAO is done
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, Integer> cart = new HashMap<>(); //Temporary row
        cart.put(1, 2); //Temporary row
        cart.put(2, 1); //Temporary row
        ProductDaoMem productList = ProductDaoMem.getInstance();
        float priceSum = 0;
        for (Map.Entry<Integer, Integer> entry: cart.entrySet()) {
            int productAmount = entry.getValue();
            Product product = productList.find(entry.getKey());
            priceSum += product.getDefaultPrice() * productAmount;
        }
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("sum", priceSum);
        engine.process("payment/payment.html", context, resp.getWriter());
    }
}
