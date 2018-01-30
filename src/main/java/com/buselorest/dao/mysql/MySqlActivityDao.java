package com.buselorest.dao.mysql;

import com.buselorest.model.domain.Activity;
import com.buselorest.dao.interfaces.AbstractJDBCDao;
import com.buselorest.dao.interfaces.ActivityDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlActivityDao extends AbstractJDBCDao<Activity> implements ActivityDao {
    private final static Logger logger = Logger.getLogger(MySqlActivityDao.class);
    @Override
    public String getSelectQuery() {
        return "SELECT id, name, description FROM Activity";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Activity (name, description) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Activity SET name = ? description = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Activity WHERE id = ?;";
    }

    @Override
    public Activity findByName(String name, Connection connection) throws SQLException {
        List<Activity> list;
        String sql = getSelectQuery();
        sql += " WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
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
    protected List<Activity> parseResultSet(ResultSet rs, Connection connection) throws SQLException {
        List<Activity> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("id"));
                activity.setName(rs.getString("name"));
                activity.setDescription(rs.getString("description"));
                result.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Activity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Activity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
        statement.setInt(3, object.getId());
    }
}
