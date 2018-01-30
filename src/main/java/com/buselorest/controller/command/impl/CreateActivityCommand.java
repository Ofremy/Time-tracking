package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateActivityCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(CreateActivityCommand.class);

    public CreateActivityCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TransactionService transactionService = serviceFactory.geTransactionService();
        UserService userService = serviceFactory.getUserService();
        ActivityService activityService = serviceFactory.getActivityService();
        try (Connection connection = pool.getConnection()) {
            Activity activity = activityService.findByName(request.getParameter("name"), connection);
            if (activity == null) {
                activity = new Activity.Builder()
                        .name(request.getParameter("name"))
                        .description(request.getParameter("description"))
                        .build();
            }
            logger.error("Create new Activity with name = " + activity.getName());
            transactionService.saveActivityForUserTransact(
                    serviceFactory,
                    connection,
                    new UsersActivity.Builder()
                            .user(userService.getUserByLogin(request.getParameter("login"), connection))
                            .activity(activity)
                            .status(UsersActivity.Status.ACTIVE)
                            .build());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.ADMIN;

    }
}
