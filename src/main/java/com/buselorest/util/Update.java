package com.buselorest.util;

import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.service.RequestService;
import com.buselorest.service.UsersActivityService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Update {
    /**
     * Update userActivities
     * @param request - @see {@link HttpServletRequest}
     * @param usersActivityService - @see {@link UsersActivityService}
     * @param connection - JDBC connection
     */
    public static void updateUserActivities(HttpServletRequest request, UsersActivityService usersActivityService,
                                            Connection connection){
        try {
            List<UsersActivity> list = usersActivityService.getLimitByUser(
                    (User)request.getSession().getAttribute("user"),
                    (Integer)request.getSession().getAttribute("pageNumber"),
                    (Integer)request.getSession().getAttribute("total"),
                    connection
            );
            Collections.sort(list, new Comparator<UsersActivity>() {
                @Override
                public int compare(UsersActivity usersActivity, UsersActivity t1) {
                    return t1.getId() - usersActivity.getId();
                }
            });
            request.getSession().setAttribute("list",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update requests
     * @param request - @see {@link HttpServletRequest}
     * @param requestService - @see {@link RequestService}
     * @param connection - JDBC connection
     */
    public static void updateRequests(HttpServletRequest request, RequestService requestService, Connection connection){
        try {
            switch (((User)request.getSession().getAttribute("user")).getRole()) {
                case USER:
                    request.getSession().setAttribute("requestList", requestService.getAll(connection));
                    break;
                case ADMIN:
                    request.getSession().setAttribute("requestList", requestService.getLimit(
                            (Integer)request.getSession().getAttribute("pageNumber"),
                            (Integer)request.getSession().getAttribute("total"),
                            connection
                    ));
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
