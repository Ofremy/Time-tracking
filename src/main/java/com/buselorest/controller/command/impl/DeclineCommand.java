package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.*;
import com.buselorest.util.TimeParser;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeclineCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(DeclineCommand.class);

    public DeclineCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestService requestService = serviceFactory.getRequestService();
        UserService userService = serviceFactory.getUserService();
        ActivityService activityService = serviceFactory.getActivityService();
        TransactionService transactionService = serviceFactory.geTransactionService();
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
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
            switch (req.getStatus()) {
                case DELETE:
                    UsersActivity usersActivityMain = null;
                    usersActivityService.getAllByLogin(user.getLogin(), connection);
                    Activity act = activityService.findByName(activityName, connection);
                    for (UsersActivity usersActivity : usersActivityService.getAllByLogin(user.getLogin(), connection)) {
                        if (usersActivity.getActivity().getName().equals(act.getName())) {
                            usersActivityMain = usersActivity;
                            if (TimeParser.parseStringTimeToLong(usersActivityMain.getTime()) == 0) {
                                usersActivityMain.setStatus(UsersActivity.Status.ACTIVE);
                            } else usersActivityMain.setStatus(UsersActivity.Status.END);
                        }
                    }
                    transactionService.addOrDeleteRequestSetUserForActivityTransact(serviceFactory, connection, usersActivityMain, req);
                    break;
                case ADD:
                    requestService.delete(req, connection);
                    break;
            }
            Update.updateRequests(request, serviceFactory.getRequestService(), connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.ADMIN;
    }
}
