package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.model.domain.User;
import com.buselorest.service.ServiceFactory;
import com.buselorest.util.Update;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PaginationCommand implements Command {
    private ServiceFactory serviceFactory;
    private DBConnectionPool pool;

    public PaginationCommand(ServiceFactory serviceFactory, DBConnectionPool pool) {
        this.serviceFactory = serviceFactory;
        this.pool = pool;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        request.getSession().setAttribute("pageNumber", pageNumber);
        try (Connection connection = pool.getConnection()) {
            switch (((User) request.getSession().getAttribute("user")).getRole()) {
                case USER:
                    Update.updateUserActivities(request, serviceFactory.getUsersActivityService(), connection);
                    break;
                case ADMIN:
                    Update.updateRequests(request, serviceFactory.getRequestService(), connection);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return PageConstants.ERROR;
        }
        return request.getParameter("uri");
    }
}
