package com.codecool.shop.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(urlPatterns = {"/payment/paypal"})
public class PayPalController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        br.close();
        System.out.println(content);
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(content.toString(), JsonObject.class);
        boolean isTransactionSuccessful;
        if (jsonObj.get("username") == null || jsonObj.get("password") == null) {
            isTransactionSuccessful = false;
        } else {
            isTransactionSuccessful = true;
        }
        resp.getWriter().println(isTransactionSuccessful);
    }
}
