package com.buselorest.dao.mysql;

import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.interfaces.AbstractJDBCDao;
import com.buselorest.dao.interfaces.UsersActivityDao;
import com.buselorest.util.TimeParser;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsersActivityDao extends AbstractJDBCDao<UsersActivity> implements UsersActivityDao {
    private final static Logger logger = Logger.getLogger(MySqlUsersActivityDao.class);
    private final static String WHERE_USERID_LIMIT_QUERY = " WHERE userId = ? LIMIT ?, ?";
    @Override
    public String getSelectQuery() {
        return "SELECT id, userId, activityId, time, status FROM UsersActivities";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO UsersActivities (userId, activityId, time, status) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE UsersActivities SET userId= ? , activityId = ? , time = ? , status = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM UsersActivities WHERE id= ?;";
    }

    @Override
    public List<UsersActivity> getLimit(User user, int currentPage, int numOfRecords, Connection connection) throws SQLException {
        List<UsersActivity> list;
        int start = currentPage * numOfRecords - numOfRecords;
        String SQL = getSelectQuery() + WHERE_USERID_LIMIT_QUERY;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, numOfRecords);
            ResultSet rs = preparedStatement.executeQuery();
            list = parseResultSet(rs, connection);
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return list;
    }

    @Override
    protected List<UsersActivity> parseResultSet(ResultSet rs, Connection connection) throws SQLException {
        List<UsersActivity> result = new ArrayList<>();
        try {
            while (rs.next()) {
                UsersActivity usersActivity = new UsersActivity();
                usersActivity.setId(rs.getInt("id"));
                usersActivity.setUser(new MySqlDaoFactory().getUserDao().getByPK(rs.getInt("userId"), connection));
                usersActivity.setActivity(new MySqlDaoFactory().getActivityDao().getByPK(rs.getInt("activityId"),
                        connection));
                usersActivity.setTime(TimeParser.parseLongToTimeString(rs.getLong("time")));
                usersActivity.setStatus(UsersActivity.Status.valueOf(rs.getString("status")));
                result.add(usersActivity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UsersActivity object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setInt(2, object.getActivity().getId());
        statement.setLong(3, TimeParser.parseStringTimeToLong(object.getTime()));
        statement.setString(4, object.getStatus().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UsersActivity object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setInt(2, object.getActivity().getId());
        statement.setLong(3, TimeParser.parseStringTimeToLong(object.getTime()));
        statement.setString(4, object.getStatus().name());
        statement.setInt(5, object.getId());
    }
}
