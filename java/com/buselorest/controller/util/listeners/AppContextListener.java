package com.buselorest.controller.util.listeners;

import com.buselorest.model.dao.connection.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        String driver = servletContext.getInitParameter("dbDriver");
        String dbURL = servletContext.getInitParameter("dbURL");
        String user = servletContext.getInitParameter("dbUser");
        String password = servletContext.getInitParameter("dbPassword");

        try {
            servletContext.setAttribute("connection",
                    DBConnectionManager.INSTANCE.setUpDataSource(driver, dbURL, user, password).getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection connection = (Connection)servletContextEvent.getServletContext().getAttribute("connection");
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
