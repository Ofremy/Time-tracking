package com.buselorest.dao.interfaces;

import com.buselorest.model.domain.Activity;

import java.sql.Connection;
import java.sql.SQLException;


public interface ActivityDao extends GenericDao<Activity> {
    /**
     * Find record with such name in DB and return this record
     * @param name - name of record in DB
     * @param connection - JDBC connection
     * @return - return Activity record from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
    Activity findByName(String name, Connection connection) throws SQLException;
}
