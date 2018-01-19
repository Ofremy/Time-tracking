package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.service.Validation;
import com.buselorest.domain.User;
import com.buselorest.model.dao.interfaces.DaoFactory;
import com.buselorest.model.dao.interfaces.GenericDao;
import com.buselorest.model.dao.mysql.MySqlDaoFactory;
import com.buselorest.model.exception.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        System.out.println(login + " it is loign");
        String errorMessage;
        if (login == null || login.equals("")){
            errorMessage = "Login can't be null or empty";
            request.setAttribute("error",errorMessage);
            return "/register.jsp";
        }
        try {
            if (Validation.isLoginExisted(login)){
                errorMessage = "Login is already exist";
                request.setAttribute("error",errorMessage);
                return "/register.jsp";
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        System.out.println(password + " = " + repeatPassword);
        if (!Validation.isPasswordMatches(password,repeatPassword)){
            errorMessage = "Password doesn't match";
            request.setAttribute("error",errorMessage);
            return "/register.jsp";
        }
        registerUser(login, password);
        return "/main.jsp";

    }

    private void registerUser(String login, String password){
        DaoFactory<Connection> daoFactory = new MySqlDaoFactory();
        try {
            Connection connection = daoFactory.getContext();
            GenericDao<User, Integer> dao = daoFactory.getDao(connection, User.class);
            System.out.println(login);
            dao.persist(new User(login, password, User.Role.USER));
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }
}
