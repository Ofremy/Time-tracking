package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("activityName", request.getParameter("activityName"));
        request.setAttribute("login", request.getParameter("login"));
        request.setAttribute("description", request.getParameter("description"));
        request.setAttribute("status", request.getParameter("status"));
        return PageConstants.ACCEPT_FORM;
    }
}
