package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ActivityService;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.TransactionService;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SubmitDeleteCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(SubmitDeleteCommand.class);

    public SubmitDeleteCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityService activityService = serviceFactory.getActivityService();
        TransactionService transactionService = serviceFactory.geTransactionService();
        try (Connection connection = pool.getConnection()) {
            Activity activity = activityService.getByPk(Integer.parseInt(request.getParameter("activityId")), connection);
            transactionService.userActivityUpdateRequestCreate(
                    serviceFactory,
                    connection,
                    new UsersActivity.Builder().
                            id(Integer.parseInt(request.getParameter("id"))).
                            user((User) request.getSession().getAttribute("user")).
                            activity(activity).
                            time(request.getParameter("time")).
                            status(UsersActivity.Status.PENDING).build(),
                    new Request((User) request.getSession().getAttribute("user"),
                            request.getParameter("name"), request.getParameter("description"),
                            Request.Status.valueOf(request.getParameter("status"))));
            Update.updateUserActivities(
                    request,
                    serviceFactory.getUsersActivityService(),
                    connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.USER_ACTIVITY;
    }
}
