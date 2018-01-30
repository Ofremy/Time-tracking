package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.ActivityService;
import com.buselorest.service.RequestService;
import com.buselorest.service.ServiceFactory;
import com.buselorest.service.UsersActivityService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class RequestExistActivityFormCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;
    private final static Logger logger = Logger.getLogger(RequestExistActivityFormCommand.class);

    public RequestExistActivityFormCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityService activityService = serviceFactory.getActivityService();
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        RequestService requestService = serviceFactory.getRequestService();
        User user = (User) request.getSession().getAttribute("user");
        try (Connection connection = pool.getConnection()) {
            List<Activity> activityList = activityService.getAll(connection);
            for (Iterator<Activity> iterator = activityList.iterator(); iterator.hasNext(); ) {
                Activity next = iterator.next();
                for (UsersActivity usersActivity : usersActivityService.getAll(connection)) {
                    if (user.getId() == usersActivity.getUser().getId() && next.getId() == usersActivity.getActivity().getId()) {
                        iterator.remove();
                    }
                }
            }
            for (Iterator<Activity> iterator = activityList.iterator(); iterator.hasNext(); ) {
                Activity next = iterator.next();
                for (Request req : requestService.getAll(connection)) {
                    if (req.getUser().getId() == user.getId() && req.getActivityName().equals(next.getName())) {
                        iterator.remove();
                    }
                }
            }
            request.setAttribute("list", activityList);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch
                (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return PageConstants.ERROR;
        }
        return PageConstants.REQUEST_ADD_EXIST_ACTIVITY_FORM;

    }

}
