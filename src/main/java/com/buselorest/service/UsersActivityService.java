package com.buselorest.service;

import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsersActivityService {
    /**
     * Find and Return all UsersActivity records from DB
     *
     * @param connection - JDBC connection
     * @return - return UsersActivity list
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<UsersActivity> getAll(Connection connection) throws SQLException;

    /**
     * Find User Activities and return limit numOfRecords records
     *
     * @param user         - user
     * @param currentPage  - current page in pagination
     * @param numOfRecords - max records per page
     * @param connection   - JDBC connection
     * @return - return usersActivity list
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<UsersActivity> getLimitByUser(User user, int currentPage, int numOfRecords, Connection connection) throws SQLException;

    /**
     * Find users activity by login and return records list
     *
     * @param login      - user login
     * @param connection - JDBC connection
     * @return - return usersActivity list
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<UsersActivity> getAllByLogin(String login, Connection connection) throws SQLException;

    /**
     * Set time for activity
     *
     * @param usersActivity - usersActivity
     * @param connection    - JDBC connection
     * @return - return usersActivity
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    UsersActivity setTime(UsersActivity usersActivity, Connection connection) throws SQLException;

    /**
     * Create user activity record in DB and return it
     *
     * @param usersActivity - userActivity what need to be created
     * @param connection    - JDBC connection
     * @return - return userActivity
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    UsersActivity create(UsersActivity usersActivity, Connection connection) throws SQLException;

    /**
     * Update user activity record in DB and return it
     *
     * @param usersActivity - user activity what need to be updated
     * @param connection    - JDBC connection
     * @return - return userActivity
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    UsersActivity update(UsersActivity usersActivity, Connection connection) throws SQLException;

    /**
     * Delete user activity record in DB
     *
     * @param usersActivity - user activity what need to be deleted
     * @param connection    - JDBC connection
     * @return - return true if user activity has been successful deleted otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean delete(UsersActivity usersActivity, Connection connection) throws SQLException;
}
