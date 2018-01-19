package com.buselorest.controller.util.filtres;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Authentication initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        logger.info("Requested resource:: " + uri);

        HttpSession session = request.getSession(false);
        System.out.println("wtf");
        System.out.println(session);
        System.out.println(uri.endsWith("jsp"));

        if (session == null && (uri.endsWith("jsp") || uri.endsWith("Login") || uri.endsWith("Register"))) {
            logger.error("Unauthorized access request");
            System.out.println("fjklfjfklsjkljfslsk");
            response.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(request, response);
        }

    }

    public void destroy() {

    }
}
