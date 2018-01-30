package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.User;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class CreateActivityFormCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(CreateExistActivityFormCommand.class);

    public CreateActivityFormCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = serviceFactory.getUserService();
        try (Connection connection = pool.getConnection()) {
            List<User> list = userService.getAll(connection);
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext(); ) {
                User next = iterator.next();
                if (User.Role.ADMIN == next.getRole()) {
                    iterator.remove();
                }
            }
            request.setAttribute("list", list);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.CREATE_ACTIVITY_FORM;
    }
}
