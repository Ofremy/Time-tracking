package com.buselorest.service.impl;

import com.buselorest.model.domain.Request;
import com.buselorest.dao.interfaces.RequestDao;
import com.buselorest.service.RequestService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    private RequestDao dao;

    public RequestServiceImpl(RequestDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Request> getAll(Connection connection) throws SQLException{
        return dao.getAll(connection);
    }

    @Override
    public Request create(Request request, Connection connection) throws SQLException {
        return dao.create(request, connection);
    }

    @Override
    public boolean delete(Request request, Connection connection) throws SQLException {
        return dao.delete(request, connection);
    }

    @Override
    public List<Request> getLimit(int currentPage, int numOfRecords, Connection connection) throws SQLException {
        return dao.getLimit(currentPage, numOfRecords, connection);
    }
}
