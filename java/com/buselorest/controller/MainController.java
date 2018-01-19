package com.buselorest.controller;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.command.CommandList;
import com.buselorest.domain.Activity;
import com.buselorest.domain.User;
import com.buselorest.model.dao.interfaces.DaoFactory;
import com.buselorest.model.dao.interfaces.GenericDao;
import com.buselorest.model.dao.interfaces.Identified;
import com.buselorest.model.dao.mysql.MySqlDaoFactory;
import com.buselorest.model.exception.PersistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/MainController")
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
        System.out.println("session is");
        String commandName = request.getParameter("action");
        Command command = CommandList.valueOf(commandName.toUpperCase()).getCommand();
        String goTo = command.execute(request,response);
        request.getRequestDispatcher(goTo).forward(request, response);
    }
}
