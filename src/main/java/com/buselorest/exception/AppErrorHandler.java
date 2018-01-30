package com.buselorest.exception;

import com.buselorest.controller.constants.PageConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AppErrorHandler")
public class AppErrorHandler extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req,resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Throwable throwable = (Throwable)req.getAttribute("javax.servlet.error.exception");
        Integer status_code = (Integer)req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null){
            servletName = "Unknown";
        }

        String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null){
            requestUri = "Unknown";
        }

        req.setAttribute("throwable", throwable);
        req.setAttribute("code",status_code);
        req.setAttribute("name", servletName);
        req.setAttribute("uri", requestUri);
        req.getRequestDispatcher(PageConstants.ERROR).forward(req,resp);


    }
}
