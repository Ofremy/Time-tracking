package com.buselorest.dao.interfaces;

import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.UsersActivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RequestDao extends GenericDao<Request> {
    /**
     * Get limit records from DB
     * @param currentPage - number of page
     * @param numOfRecords - max count of records per page
     * @param connection - JDBC connection
     * @return - return request list of objects
     * @throws SQLException =- - if something is bad with SQL {@link SQLException}
     */
    List<Request> getLimit(int currentPage, int numOfRecords, Connection connection) throws SQLException;
}
