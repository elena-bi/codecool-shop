package com.codecool.shop.controller;

import com.codecool.shop.service.ConfirmationEmailService;
import com.codecool.shop.util.RequestToJsonObject;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment/card"})
public class BankCardController extends HttpServlet {
    private ConfirmationEmailService ces = ConfirmationEmailService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObj = RequestToJsonObject.getJsonObjectFromRequest(req);
        boolean isTransactionSuccessful;
        if (jsonObj.get("cardNumber") == null || jsonObj.get("cardHolder") == null || jsonObj.get("expiryDate") == null || jsonObj.get("safetyCode") == null) {
            isTransactionSuccessful = false;
        } else {
            isTransactionSuccessful = true;
            ces.sendOrderConfirmationEmail();
        }
        resp.getWriter().println(isTransactionSuccessful);
    }
}

