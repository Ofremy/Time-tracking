package com.buselorest.service;

import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.UsersActivity;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionService {
    /**
     * if activity is not created and user doesn't have this activity,
     * save activity for user
     * @param serviceFactory - serviceFactory @see {@link ServiceFactory}
     * @param connection - JDBC connection
     * @param usersActivity - user activity
     * @return - return true if saveActivityForUserTransact has been ended successful otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean saveActivityForUserTransact(ServiceFactory serviceFactory,
                                        Connection connection,
                                        UsersActivity usersActivity) throws SQLException;

    /**
     * Delete add or delete request base on request status and
     * set activity or create if not exist for user
     * @param serviceFactory - serviceFactory @see {@link ServiceFactory}
     * @param connection - JDBC connection
     * @param usersActivity - usersActivity
     * @param request - request
     * @return true if transact has been ended successful otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean addOrDeleteRequestSetUserForActivityTransact(ServiceFactory serviceFactory,
                                                         Connection connection,
                                                         UsersActivity usersActivity,
                                                         Request request) throws SQLException;

    /**
     * Delete request and set activity for user
     * @param serviceFactory - serviceFactory @see {@link ServiceFactory}
     * @param connection - JDBC connection
     * @param request - request
     * @param usersActivity - usersActivity
     * @return true if transact has been ended successful otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean deleteRequestSetActivityForUserTransact(ServiceFactory serviceFactory,
                                                    Connection connection,
                                                    Request request,
                                                    UsersActivity usersActivity) throws SQLException;

    void userActivityUpdateRequestCreate(ServiceFactory serviceFactory,
                                         Connection connection,
                                         UsersActivity usersActivity,
                                         Request request);
}
