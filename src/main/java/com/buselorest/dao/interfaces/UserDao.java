package com.buselorest.dao.interfaces;

import com.buselorest.model.domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao extends GenericDao<User> {
    /**
     * Get user record form DB
     * @param login - login of record in DB
     * @param connection - JDBC connection
     * @return - return User record from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    User findByLogin(String login, Connection connection) throws SQLException;
}
