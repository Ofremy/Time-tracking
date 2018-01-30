package com.buselorest.dao.interfaces;

import com.buselorest.model.domain.Entity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract JDBC Dao with CRUD realisation
 * @param <T> extends Entity
 */
public abstract class AbstractJDBCDao<T extends Entity> implements GenericDao<T> {
    private final String WHERE_LAST_INSERT_ID_QUERY = " WHERE id = last_insert_id();";
    private final String WHERE_ID_QUERY = " WHERE id = ?;";
    private final static Logger logger = Logger.getLogger(AbstractJDBCDao.class);

    /**
     *
     * @return String - SQL SELECT query
     * SQL query - SELECT * FROM [TABLE]
     */
    public abstract String getSelectQuery();

    /**
     *
     * @return String - SQL CREATE query
     * SQL query - INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     *
     * @return String - SQL UPDATE query
     * SQL query - UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     *
     * @return String - SQL DELETE query
     * SQL query - DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Method parse ResultSet and return list of this parse
     * @param rs - ResultSet to be parsed
     * @param connection - JDBC connection
     * @return generic List
     * @throws SQLException - if some problem with SQL
     */
    protected abstract List<T> parseResultSet(ResultSet rs, Connection connection) throws SQLException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */

    /**
     * Set object fields in insert statement
     * @param statement - PreparedStatement
     * @param object - Object what need to be inserted
     * @throws SQLException - if some problem with SQL
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    /**
     * Устанавливает аргументы updateUserActivities запроса в соответствии со значением полей объекта object.
     */

    /**
     * Set object fields in update statement
     * @param statement - PreparedStatement
     * @param object - Object what need to be updated
     * @throws SQLException - if some problem WITH SQL
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    @Override
    public T create(T object, Connection connection) throws SQLException {
        T persistInstance;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        sql = getSelectQuery() + WHERE_LAST_INSERT_ID_QUERY;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs, connection);
            if ((list == null) || (list.size() != 1)) {
                throw new SQLException();
            }
            persistInstance = list.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return persistInstance;
    }


    @Override
    public T getByPK(int key, Connection connection) throws SQLException {
        List<T> list;
        String sql = getSelectQuery() + WHERE_ID_QUERY;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new SQLException();
        }
        return list.iterator().next();
    }

    @Override
    public T update(T object, Connection connection) throws SQLException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return object;
    }

    @Override
    public boolean delete(T object, Connection connection) throws SQLException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return true;
    }

    @Override
    public List<T> getAll(Connection connection) throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return list;
    }

}
