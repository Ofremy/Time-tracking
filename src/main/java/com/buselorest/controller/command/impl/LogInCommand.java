package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.MessageConstants;
import com.buselorest.exception.WrongInputException;
import com.buselorest.model.domain.User;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.*;
import com.buselorest.util.MD5Util;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import static com.buselorest.controller.constants.PageConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LogInCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(LogInCommand.class);

    public LogInCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = serviceFactory.getUserService();
        RequestService requestService = serviceFactory.getRequestService();
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user;

        try (Connection connection = pool.getConnection()) {
            try {
                user = userService.login(new User.Builder().login(login).password(MD5Util.md5Apache(password)).build(), connection);
                if (user == null) {
                    request.setAttribute("error", ResourceManager.INSTANCE.getString(MessageConstants.USER_NOT_FIND));
                    return LOGIN;
                }
            } catch (WrongInputException e) {
                request.setAttribute("error", e.getMessage());
                return LOGIN;
            }
            session.setAttribute("pageNumber", 1);
            session.setAttribute("total", 5);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60);
            logger.info("User with login" + user.getLogin() + " log in");
            switch (user.getRole()) {
                case USER:
                    Update.updateUserActivities(request, usersActivityService, connection);
                    Update.updateRequests(request, requestService, connection);
                    return USER_ACTIVITY;
                case ADMIN:
                    Update.updateRequests(request, requestService, connection);
                    return ADMIN;
                default:
                    return ERROR;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return ERROR;
        }
    }
}
