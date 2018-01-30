package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UsersActivityService;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateIndexCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(UpdateIndexCommand.class);

    public UpdateIndexCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        try (Connection connection = pool.getConnection()) {
            request.getSession().setAttribute("list", usersActivityService.getAll(connection));
            Update.updateUserActivities(
                    request,
                    usersActivityService,
                    connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.USER_ACTIVITY;
    }
}
