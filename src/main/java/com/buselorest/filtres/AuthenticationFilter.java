package com.buselorest.filtres;

import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.User;
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
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }
        if (user == null && !uri.endsWith("/jsp/login.jsp") && !uri.endsWith("/jsp/register.jsp")) {
            response.sendRedirect(PageConstants.LOGIN);
        } else {
            if (user != null && (uri.endsWith("/jsp/login.jsp") || uri.endsWith("/jsp/register.jsp"))) {
               response.sendRedirect(PageConstants.USER_ACTIVITY);
            }
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
