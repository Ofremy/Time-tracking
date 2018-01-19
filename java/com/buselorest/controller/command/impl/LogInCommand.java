package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.service.Validation;
import com.buselorest.domain.User;
import com.buselorest.model.exception.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println("it is log= " + login);
        User user = null;
        System.out.println("OUCH");
        try {
            user = Validation.getUser(login,password);
            if (user == null) {
                request.setAttribute("error","User with such login and password doesn't exist");
                return "/login.jsp";
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }
        System.out.println("session is");
        HttpSession session = request.getSession();
        session.setAttribute("login",user.getLogin());
        session.setAttribute("password",user.getPassword());
        session.setAttribute("role",user.getRole());
        session.setMaxInactiveInterval(30*60);
        System.out.println(session);
        return "index.html";
    }
}
