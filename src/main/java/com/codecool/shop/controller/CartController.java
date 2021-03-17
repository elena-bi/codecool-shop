package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.JsonArray;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import com.codecool.hackernews.JsonHandler;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            CartDao shoppingCart = CartDaoMem.getInstance();
            HashMap cart = shoppingCart.getCartMap();

            ProductDao productsList = ProductDaoMem.getInstance();
            List<Product> cartContents = new ArrayList<>();
//            List<Integer> productQuantity = new ArrayList<>();

            Iterator it = cart.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                productsList.find((int)pair.getKey()).setQuantity((int)pair.getValue());
//                productQuantity.add((int)pair.getValue());
                cartContents.add(productsList.find((int)pair.getKey()));
                it.remove();
            }

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
//            context.setVariable("quantities", productQuantity);
            context.setVariable("products", cartContents);
            // // Alternative setting of the template context
            // Map<String, Object> params = new HashMap<>();
            // params.put("category", productCategoryDataStore.find(1));
            // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            // context.setVariables(params);
            engine.process("cart.html", context, resp.getWriter());
    }
}
