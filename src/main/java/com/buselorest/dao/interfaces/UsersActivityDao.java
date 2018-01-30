package com.buselorest.dao.interfaces;

import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsersActivityDao extends GenericDao<UsersActivity> {
    /**
     *
     * @param user - user in DB
     * @param currentPage - number of page
     * @param numOfRecords - max count of records per page
     * @param connection - JDBC connection
     * @return - return UserActivity record from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<UsersActivity> getLimit(User user, int currentPage, int numOfRecords, Connection connection) throws SQLException;
}
