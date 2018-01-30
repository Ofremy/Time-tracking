package com.buselorest.dao.mysql;

import com.buselorest.dao.interfaces.UserDao;
import com.buselorest.model.domain.User;
import com.buselorest.dao.interfaces.AbstractJDBCDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends AbstractJDBCDao<User> implements UserDao {
    private final static Logger logger = Logger.getLogger(MySqlUserDao.class);
    public final String WHERE_LOGIN_QUERY = " WHERE login = ?";

    @Override
    public String getSelectQuery() {
        return "SELECT id, login, password, firstName, lastName, role FROM Users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Users (login, password, firstName, lastName, role) \n" +
                "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Users SET login= ? , password = ? , " +
                "firstName = ? , lastName = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Users WHERE id = ?;";
    }

    @Override
    public User findByLogin(String login, Connection connection) throws SQLException {
        List<User> list;
        String sql = getSelectQuery();
        sql += WHERE_LOGIN_QUERY;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
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
    protected List<User> parseResultSet(ResultSet rs, Connection connection) throws SQLException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setRole(User.Role.valueOf(rs.getString("role")));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getFirstName());
        statement.setString(4, object.getLastName());
        statement.setString(5, object.getRole().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getFirstName());
        statement.setString(4, object.getLastName());
        statement.setInt(5, object.getId());
    }
}
