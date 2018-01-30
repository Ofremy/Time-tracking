package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.UsersActivity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTimeFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("description", request.getParameter("description"));
        request.setAttribute("time", request.getParameter("time"));
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("activityId", request.getParameter("activityId"));
        request.setAttribute("status", UsersActivity.Status.END);
        return PageConstants.SET_TIME_FORM;
    }
}
