package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ActivityService;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.TransactionService;
import com.buselorest.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateExistActivityCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(CreateExistActivityCommand.class);

    public CreateExistActivityCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActivityService activityService = serviceFactory.getActivityService();
        UserService userService = serviceFactory.getUserService();
        TransactionService transactionService = serviceFactory.geTransactionService();
        try (Connection connection = pool.getConnection()) {
            transactionService.saveActivityForUserTransact(
                    serviceFactory,
                    connection,
                    new UsersActivity.Builder().
                            user(userService.getUserByLogin(request.getParameter("login"), connection)).
                            activity(activityService.getByPk(Integer.parseInt(request.getParameter("id")), connection)).
                            status(UsersActivity.Status.ACTIVE).build()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }

        return PageConstants.ADMIN;
    }
}
