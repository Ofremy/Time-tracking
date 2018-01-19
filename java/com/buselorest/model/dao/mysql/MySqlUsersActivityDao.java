package com.buselorest.model.dao.mysql;

import com.buselorest.domain.UsersActivity;
import com.buselorest.model.dao.interfaces.AbstractJDBCDao;
import com.buselorest.model.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsersActivityDao extends AbstractJDBCDao<UsersActivity, Integer> {
    private class PersistUsersActivity extends UsersActivity {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, userId, activityId, time FROM UserActivities";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO UserActivities (userId, activityId, time) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE UserActivities SET userId= ? activityId = ? time = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM UserActivities WHERE id= ?;";
    }

    @Override
    public UsersActivity create() throws PersistException {
        UsersActivity UsersActivity = new UsersActivity();
        return persist(UsersActivity);
    }

    public MySqlUsersActivityDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<UsersActivity> parseResultSet(ResultSet rs) throws PersistException {
        List<UsersActivity> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistUsersActivity usersActivity = new PersistUsersActivity();
                usersActivity.setId(rs.getInt("id"));
                usersActivity.setUserId(rs.getInt("userId"));
                usersActivity.setActivityId(rs.getInt("activityId"));
                usersActivity.setTime(rs.getTime("time"));
                result.add(usersActivity);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UsersActivity object) throws PersistException {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getActivityId());
            statement.setTime(3, object.getTime());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UsersActivity object) throws PersistException {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getActivityId());
            statement.setTime(3, object.getTime());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
