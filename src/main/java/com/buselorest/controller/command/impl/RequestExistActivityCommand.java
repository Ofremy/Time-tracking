package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ActivityService;
import com.buselorest.service.RequestService;
import com.buselorest.service.ServiceFactory;
import com.buselorest.util.Update;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RequestExistActivityCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(RequestExistActivityCommand.class);

    public RequestExistActivityCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestService requestService = serviceFactory.getRequestService();
        ActivityService activityService = serviceFactory.getActivityService();
        User user = (User) request.getSession().getAttribute("user");
        try (Connection connection = pool.getConnection()) {
            Activity activity = activityService.getByPk(Integer.parseInt(request.getParameter("id")), connection);
            Update.updateRequests(request, requestService, connection);
            for (Request request1 : (List<Request>) request.getSession().getAttribute("requestList")) {
                if (request1.getUser().getId() == user.getId() && request1.getActivityName().equals(activity.getName())) {
                    return PageConstants.USER_ACTIVITY;
                }
            }
            Request req = requestService.create(new Request((User) request.getSession().getAttribute("user"),
                    activity.getName(), activity.getDescription(), Request.Status.ADD), connection);
            logger.info("User with id = " + user.getId() + " request new Activity = " + req.getActivityName());

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.USER_ACTIVITY;
    }
}
