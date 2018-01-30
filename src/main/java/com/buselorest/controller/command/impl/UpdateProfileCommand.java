package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.MessageConstants;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.exception.WrongInputException;
import com.buselorest.model.domain.User;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ResourceManager;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UserService;
import com.buselorest.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateProfileCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(UpdateProfileCommand.class);

    public UpdateProfileCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = serviceFactory.getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        User user = (User) request.getSession().getAttribute("user");

        try (Connection connection = pool.getConnection()) {
            if (!user.getLogin().equals(login)) {
                if (userService.getUserByLogin(login, connection) != null) {
                    request.setAttribute("error",
                            ResourceManager.INSTANCE.getString(MessageConstants.USER_EXIST));
                    return PageConstants.PROFILE;
                }
            }
            if (!password.equals(repeatPassword)) {
                request.setAttribute("error",
                        ResourceManager.INSTANCE.getString(MessageConstants.PASSWORD_NOT_MATCH));
                return PageConstants.PROFILE;
            }
            user = new User.Builder()
                    .id(user.getId())
                    .login(login)
                    .password(MD5Util.md5Apache(password))
                    .firstName(request.getParameter("firstName"))
                    .lastName(request.getParameter("lastName"))
                    .build();
            userService.update(user, connection);
            request.getSession().setAttribute("user", user);
        } catch (WrongInputException e) {
            request.setAttribute("error", e.getMessage());
            return PageConstants.PROFILE;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.PROFILE;
    }
}
