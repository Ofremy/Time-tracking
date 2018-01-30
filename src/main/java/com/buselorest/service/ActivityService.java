package com.buselorest.service;

import com.buselorest.model.domain.Activity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ActivityService {
    /**
     * Return all Activity records from DB
     *
     * @param connection - JDBC connection
     * @return - return Activity records from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    List<Activity> getAll(Connection connection) throws SQLException;

    /**
     * Return Activity record from DB by primary key
     *
     * @param key        - primary key
     * @param connection - JDBC connection
     * @return - return Activity by primary key
     * @throws SQLException - - if something is bad with SQL {@link SQLException}
     */
    Activity getByPk(int key, Connection connection) throws SQLException;

    /**
     * Create activity record in DB
     *
     * @param activity   - activity to be created in DB
     * @param connection - JDBC connection
     * @return - return created Activity record from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    Activity save(Activity activity, Connection connection) throws SQLException;

    /**
     * Find record with such name in DB and then return this record
     *
     * @param name       - activity name
     * @param connection - JDBC connection
     * @return - return  Activity record
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    Activity findByName(String name, Connection connection) throws SQLException;

    /**
     * Delete activity record in DB
     *
     * @param activity   - activity
     * @param connection - JDBC connection
     * @return - return true if activity has benn deleted otherwise return false
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    boolean delete(Activity activity, Connection connection) throws SQLException;
}
