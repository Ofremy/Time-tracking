package com.buselorest.model.dao.mysql;

import com.buselorest.domain.Activity;
import com.buselorest.model.dao.interfaces.AbstractJDBCDao;
import com.buselorest.model.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlActivityDao extends AbstractJDBCDao<Activity, Integer> {
    private class PersistActivity extends Activity {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name FROM Activity";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Activity (name) \n" +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Activity SET name = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Activity WHERE id = ?;";
    }

    @Override
    public Activity create() throws PersistException {
        Activity Activity = new Activity();
        return persist(Activity);
    }

    public MySqlActivityDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Activity> parseResultSet(ResultSet rs) throws PersistException {
        List<Activity> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistActivity activity = new PersistActivity();
                activity.setId(rs.getInt("id"));
                activity.setName(rs.getString("name"));
                result.add(activity);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Activity object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Activity object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
