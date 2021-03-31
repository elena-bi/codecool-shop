package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final UserDaoMem userDaoMem = UserDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("login/login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("user-name");
        String password = req.getParameter("user-password");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (username == null || password == null) {
            context.setVariable("failed", true);
            engine.process("login/login.html", context, resp.getWriter());
            return;
        }
        Optional<User> user = userDaoMem.getUserWithNamePassword(username, password);
        if (user.isEmpty()) {
            context.setVariable("failed", true);
            engine.process("login/login.html", context, resp.getWriter());
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user.get().getId());
        resp.sendRedirect("/");
    }
}
