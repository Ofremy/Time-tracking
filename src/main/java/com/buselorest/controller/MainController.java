package com.buselorest.controller;

import com.buselorest.controller.command.ActionFactory;
import com.buselorest.controller.command.Command;
import com.buselorest.controller.constants.PageConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/app/*")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        Command command = client.defineCommand(request);
        String goTo = command.execute(request,response);
        if (goTo != null){
            request.getRequestDispatcher(goTo).forward(request, response);
        }else {
            response.sendRedirect(PageConstants.ERROR);
        }
    }
}
