package com.buselorest.model.dao.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public enum DBConnectionManager {
    INSTANCE;

    private BasicDataSource basicDataSource;

    public DataSource setUpDataSource(String driver, String dbURL, String user, String password) {
        if (basicDataSource == null) {
            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(driver);
            basicDataSource.setUrl(dbURL);
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(password);

            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(20);
            basicDataSource.setMaxOpenPreparedStatements(180);
        }
        return basicDataSource;
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
