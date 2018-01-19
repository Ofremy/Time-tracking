package com.buselorest.model.dao.mysql;

import com.buselorest.domain.Activity;
import com.buselorest.domain.Request;
import com.buselorest.model.dao.interfaces.AbstractJDBCDao;
import com.buselorest.model.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlRequestDao extends AbstractJDBCDao<Request, Integer> {
    private class PersistRequest extends Request {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, userId, activityId, status FROM Request";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Request (userId, activityId, status) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Request SET userId = ? activityId = ? status = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Request WHERE id = ?;";
    }

    @Override
    public Request create() throws PersistException {
        Request request = new Request();
        return persist(request);
    }

    public MySqlRequestDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Request> parseResultSet(ResultSet rs) throws PersistException {
        List<Request> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistRequest request = new PersistRequest();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("userId"));
                request.setActivityId(rs.getInt("activityId"));
                String status = rs.getString("status");
                if (status.equalsIgnoreCase(Request.Status.ACCEPT.name())){
                    request.setStatus(Request.Status.ACCEPT);
                }else if (status.equalsIgnoreCase(Request.Status.DECLINE.name())){
                    request.setStatus(Request.Status.DECLINE);
                }else request.setStatus(Request.Status.PROCESSING);
                result.add(request);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Request object) throws PersistException {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getActivityId());
            statement.setString(3, object.getStatus().name());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Request object) throws PersistException {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getActivityId());
            statement.setString(3, object.getStatus().name());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
