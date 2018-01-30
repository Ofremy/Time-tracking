package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.MessageConstants;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.exception.WrongInputException;
import com.buselorest.service.ResourceManager;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UserService;
import com.buselorest.model.domain.User;
import com.buselorest.util.MD5Util;
import org.apache.log4j.Logger;

import static com.buselorest.controller.constants.PageConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(RegisterCommand.class);

    public RegisterCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = serviceFactory.getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (!(password.equals(repeatPassword))) {
            request.setAttribute("error",
                    ResourceManager.INSTANCE.getString(MessageConstants.PASSWORD_NOT_MATCH));
            return REGISTER;
        }
        try (Connection connection = pool.getConnection()) {
            User user = userService.register(
                    new User.Builder().
                            login(login).
                            password(MD5Util.md5Apache(password)).
                            firstName(firstName).
                            lastName(lastName).build(), connection);
            logger.info("New user registered. Login = " + user.getLogin());
        } catch (WrongInputException e) {
            request.setAttribute("error", e.getMessage());
            return REGISTER;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return ERROR;
        }
        return LOGIN;
    }


}
