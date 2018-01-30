package com.buselorest.service;

import com.buselorest.model.domain.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RequestService {
    /**
     * Return list of all Request records
     *
     * @param connection - JDBC connection
     * @return - return all Request records
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<Request> getAll(Connection connection) throws SQLException;

    /**
     * Return numOfRecords limit records from DB
     *
     * @param currentPage  - currentPage
     * @param numOfRecords - max records per page
     * @param connection   - JDBC connection
     * @return - limited Request records
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<Request> getLimit(int currentPage, int numOfRecords, Connection connection) throws SQLException;

    /**
     * Create request record in DB and return created record
     *
     * @param request    - Request what need to be created
     * @param connection - JDBC connection
     * @return - return created Request record
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    Request create(Request request, Connection connection) throws SQLException;

    /**
     * @param request    - Request what need to be deleted
     * @param connection - JDBC connection
     * @return - return true if request has benn deleted otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean delete(Request request, Connection connection) throws SQLException;
}
