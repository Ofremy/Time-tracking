package com.buselorest.filtres;

import com.buselorest.controller.constants.PageConstants;
import com.buselorest.model.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class RoleFilter implements Filter {
    private List<String> adminPages = new ArrayList<>();
    private List<String> userPages = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminPages = Arrays.asList(
                "/jsp/admin.jsp",
                "/jsp/form/createActivityForm.jsp",
                "/jsp/form/createExistActivityForm.jsp",
                "/jsp/form/acceptForm.jsp",
                "/jsp/form/declineForm.jsp"
        );
        userPages = Arrays.asList(
                "/jsp/userActivity.jsp",
                "/jsp/form/requestAddActivityForm.jsp",
                "/jsp/form/requestAddExistActivityForm.jsp",
                "/jsp/form/requestDeleteActivityForm.jsp",
                "/jsp/form/setTimeForm.jsp",
                "/jsp/profile.jsp"
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User.Role role = ((User)request.getSession().getAttribute("user")).getRole();
        String uri = request.getRequestURI();
        boolean isRedirected = false;
        switch (role){
            case USER:
                for (String adminPages : adminPages) {
                    if (adminPages.equals(uri)){
                        response.sendRedirect(PageConstants.USER_ACTIVITY);
                        isRedirected = true;
                        break;
                    }
                }
                break;
            case ADMIN:
                for (String userPage : userPages) {
                    if (userPage.equals(uri)){
                        response.sendRedirect(PageConstants.ADMIN);
                        isRedirected = true;
                        break;
                    }
                }
                break;
                default:response.sendRedirect(PageConstants.ERROR);
        }
        if(!isRedirected) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
