package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ConfirmationEmailService;
import com.codecool.shop.service.HashService;
import com.codecool.shop.service.RegistrationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
    private final HashService hashService = new HashService();
    private final ConfirmationEmailService ces = new ConfirmationEmailService();
    private final RegistrationService registrationService = new RegistrationService(hashService, ces);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("registration/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("user-name");
        String email = req.getParameter("user-email");
        String password = req.getParameter("user-password");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (!registrationService.register(username, email, password)) {
            context.setVariable("failed", true);
        } else {
            context.setVariable("failed", false);
        }

        engine.process("registration/registration.html", context, resp.getWriter());
    }
}
