package com.buselorest.model.dao.mysql;

import com.buselorest.domain.Activity;
import com.buselorest.domain.Request;
import com.buselorest.domain.User;
import com.buselorest.domain.UsersActivity;
import com.buselorest.model.dao.connection.DBConnectionManager;
import com.buselorest.model.dao.interfaces.DaoFactory;
import com.buselorest.model.dao.interfaces.GenericDao;
import com.buselorest.model.exception.PersistException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection;
        try {
            connection = DBConnectionManager.INSTANCE.getConnection();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        creators = new HashMap<Class, DaoCreator>();
        creators.put(Activity.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlActivityDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(connection);
            }
        });
        creators.put(UsersActivity.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUsersActivityDao(connection);
            }
        });
        creators.put(Request.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRequestDao(connection);
            }
        });
    }
}
