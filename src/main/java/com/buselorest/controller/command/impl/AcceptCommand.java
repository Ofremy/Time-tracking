package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.*;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import static com.buselorest.controller.constants.PageConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AcceptCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(AcceptCommand.class);

    public AcceptCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = serviceFactory.getUserService();
        ActivityService activityService = serviceFactory.getActivityService();
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        TransactionService transactionService = serviceFactory.geTransactionService();

        try (Connection connection = pool.getConnection()) {
            User user = userService.getUserByLogin(request.getParameter("login"), connection);
            String activityName = request.getParameter("activityName");
            String description = request.getParameter("description");
            Request req = new Request(
                    Integer.parseInt(request.getParameter("id")),
                    user,
                    activityName,
                    description,
                    Request.Status.valueOf(request.getParameter("status"))
            );
            UsersActivity usersActivity = null;
            Activity activity;
            switch (req.getStatus()) {
                case DELETE:
                    activity = activityService.findByName(activityName, connection);
                    for (UsersActivity uA : usersActivityService.getAll(connection)) {
                        if (uA.getUser().getId() == user.getId()
                                && uA.getActivity().getId() == activity.getId()) {
                            usersActivity = uA;
                        }
                    }
                    break;
                case ADD:
                    activity = activityService.findByName(activityName, connection);
                    if (activity == null) {
                        activity = new Activity.Builder().name(activityName).description(description).build();
                    }

                    usersActivity = new UsersActivity.Builder()
                            .user(user)
                            .activity(activity)
                            .status(UsersActivity.Status.ACTIVE)
                            .build();
            }
            transactionService.deleteRequestSetActivityForUserTransact(serviceFactory, connection, req, usersActivity);
            Update.updateRequests(request, serviceFactory.getRequestService(), connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return ADMIN;
    }
}
