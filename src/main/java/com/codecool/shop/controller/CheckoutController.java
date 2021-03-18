package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.dao.implementation.UserDetailsDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDetailsDao userDetails = UserDetailsDaoMem.getInstance();

        String userName = request.getParameter("name");
        String userEmailAddress = request.getParameter("email-address");
        String userDeliveryAddress = request.getParameter("delivery-address");
        String phoneNumber = request.getParameter("phone-number");
        String bankName = request.getParameter("bank");
        String userCardNumber = request.getParameter("card-number");

        userDetails.setUserName(userName);
        userDetails.setUserEmailAddress(userEmailAddress);
        userDetails.setUserDeliveryAddress(userDeliveryAddress);
        userDetails.setPhoneNumber(phoneNumber);
        userDetails.setBankName(bankName);
        userDetails.setUserCardNumber(userCardNumber);
        response.sendRedirect("/payment");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("Checkout.html", context, response.getWriter());
    }

}
