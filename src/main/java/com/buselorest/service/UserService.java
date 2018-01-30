package com.buselorest.service;

import com.buselorest.exception.WrongInputException;
import com.buselorest.model.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * Find User record in DB by user login and return this record
     *
     * @param login      - user login
     * @param connection - JDBC connection
     * @return - return User record
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    User getUserByLogin(String login, Connection connection) throws SQLException;

    /**
     * Register user record in DB and return this record from DB
     *
     * @param user       - user what need to be registered
     * @param connection - JDBC connection
     * @return - return registered User record
     * @throws WrongInputException - if input isn't valid throw{@link WrongInputException}
     * @throws SQLException        - if something is bad with SQL {@link SQLException}
     */
    User register(User user, Connection connection) throws WrongInputException, SQLException;

    /**
     * Check is user registered and returned user record from db if registered
     *
     * @param user       - user what need to be log in
     * @param connection - JDBC connection
     * @return - User record from DB
     * @throws WrongInputException - if input isn't valid throws{@link WrongInputException}
     * @throws SQLException        - if something is bad with SQL {@link SQLException}
     */
    User login(User user, Connection connection) throws WrongInputException, SQLException;

    /**
     * Update user record in DB
     *
     * @param user       - user what need to be updated
     * @param connection - JDBC connection
     * @return - return updated user record
     * @throws WrongInputException - if input isn't valid throws{@link WrongInputException}
     * @throws SQLException        - if something is bad with SQL {@link SQLException}
     */
    User update(User user, Connection connection) throws WrongInputException, SQLException;

    /**
     * Find and return all User records in DB
     *
     * @param connection - JDBC connection
     * @return - return users list of records
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<User> getAll(Connection connection) throws SQLException;
}
