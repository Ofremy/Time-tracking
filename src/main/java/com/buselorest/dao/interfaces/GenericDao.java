package com.buselorest.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    /**
     * Create an object in DB and return this object from DB
     * @param object - Object what need to be created
     * @param connection - JDBC connection
     * @return - return created Object
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
     T create(T object, Connection connection) throws SQLException;


    /**
     * Return an object by primary key from DB
     * @param key - Primary key of object in DB
     * @param connection - JDBC connection
     * @return - return created Object from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
     T getByPK(int key, Connection connection) throws SQLException;


    /**
     * Update an object in DB and return this object from DB
     * @param object - Object what need to be updated
     * @param connection - JDBC connection
     * @return - return updated Object from DB
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
     T update(T object, Connection connection) throws SQLException;


    /**
     * Return boolean value of object delete result in DB
     * @param object - Object what need to be deleted
     * @param connection - JDBC connection
     * @return - return boolean result of delete
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
     boolean delete(T object, Connection connection) throws SQLException;


    /**
     * Find all records in DB and return them as List
     * @param connection - JDBC connection
     * @return - generic List
     * @throws SQLException - if something is bad with SQL {@link SQLException}
     */
     List<T> getAll(Connection connection) throws SQLException;
}
