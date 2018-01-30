package com.buselorest.dao.mysql;

import com.buselorest.model.domain.Request;
import com.buselorest.dao.interfaces.AbstractJDBCDao;
import com.buselorest.dao.interfaces.RequestDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlRequestDao extends AbstractJDBCDao<Request> implements RequestDao {
    private final static Logger logger = Logger.getLogger(MySqlRequestDao.class);
    @Override
    public String getSelectQuery() {
        return "SELECT id, userId, activityName, description, status FROM Request ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Request (userId, activityName, description, status) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Request SET userId = ? , activityName = ? , description = ? , status = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Request WHERE id = ?;";
    }


    @Override
    public List<Request> getLimit(int currentPage, int numOfRecords, Connection connection) throws SQLException {
        List<Request> list;
        int start = currentPage * numOfRecords - numOfRecords;
        String SQL = getSelectQuery() + " LIMIT ?, ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, numOfRecords);
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
    protected List<Request> parseResultSet(ResultSet rs, Connection connection) throws SQLException {
        List<Request> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("id"));
                request.setUser(new MySqlDaoFactory().getUserDao().getByPK(rs.getInt("userId"), connection));
                request.setActivityName(rs.getString("activityName"));
                request.setDescription(rs.getString("description"));
                request.setStatus(Request.Status.valueOf(rs.getString("status")));
                result.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Request object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setString(2, object.getActivityName());
        statement.setString(3, object.getDescription());
        statement.setString(4, object.getStatus().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Request object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setString(2, object.getActivityName());
        statement.setString(3, object.getDescription());
        statement.setString(4, object.getStatus().name());
        statement.setInt(5, object.getId());


    }
}
