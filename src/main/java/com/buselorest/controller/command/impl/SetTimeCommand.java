package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UsersActivityService;
import com.buselorest.util.TimeParser;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SetTimeCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(SetTimeCommand.class);

    public SetTimeCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        if (!TimeParser.timeMatchesRegex(request.getParameter("time"))) {
                return PageConstants.USER_ACTIVITY;
        }
        User user = (User) request.getSession().getAttribute("user");
        try (Connection connection = pool.getConnection()) {
            usersActivityService.setTime(new UsersActivity.Builder().
                    id(Integer.parseInt(request.getParameter("id"))).
                    user(user).
                    activity(new Activity.Builder().id(Integer.parseInt(request.getParameter("activityId"))).build())
                    .time(request.getParameter("time")).
                            status(UsersActivity.Status.valueOf(request.getParameter("status"))).
                            build(), connection);
            Update.updateUserActivities(request, usersActivityService, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.USER_ACTIVITY;
    }
}
