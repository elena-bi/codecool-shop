package com.codecool.shop.controller;

import com.codecool.shop.util.ConfirmationEmailManager;
import com.codecool.shop.util.RequestToJsonObject;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment/paypal"})
public class PayPalController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObj = RequestToJsonObject.getJsonObjectFromRequest(req);
        boolean isTransactionSuccessful;
        if (jsonObj.get("username") == null || jsonObj.get("password") == null) {
            isTransactionSuccessful = false;
        } else {
            isTransactionSuccessful = true;
            ConfirmationEmailManager.sendConfirmationEmail();
        }
        resp.getWriter().println(isTransactionSuccessful);
    }
}
