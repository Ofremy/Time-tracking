package com.buselorest.dao.connection;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool realisation
 */
public enum DBConnectionPool {
    INSTANCE;
    private final static Logger logger = Logger.getLogger(DBConnectionPool.class);
    private DataSource dataSource;
    {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource)initialContext.lookup("java:comp/env/jdbc/tracking");
        } catch (NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Get connection from the pool
     * @return - Connection
     * @throws SQLException - - if something is bad with SQL {@link SQLException}
     */
    public Connection getConnection() throws SQLException{
        try {
            logger.info("create new connection");
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

}
