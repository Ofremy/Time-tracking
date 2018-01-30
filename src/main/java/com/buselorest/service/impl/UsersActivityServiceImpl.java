package com.buselorest.service.impl;

import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.dao.interfaces.UsersActivityDao;
import com.buselorest.service.UsersActivityService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class UsersActivityServiceImpl implements UsersActivityService {
    private UsersActivityDao dao;

    public UsersActivityServiceImpl(UsersActivityDao dao) {
        this.dao = dao;
    }

    @Override
    public List<UsersActivity> getAll(Connection connection) throws SQLException {
        return dao.getAll(connection);
    }

    @Override
    public List<UsersActivity> getLimitByUser(User user, int currentPage, int numOfRecords, Connection connection) throws SQLException {
        return dao.getLimit(user, currentPage, numOfRecords, connection);
    }

    @Override
    public List<UsersActivity> getAllByLogin(String login, Connection connection) throws SQLException {
        List<UsersActivity> list = dao.getAll(connection);
        for (Iterator<UsersActivity> iterator = list.iterator(); iterator.hasNext(); ) {
            UsersActivity next = iterator.next();
            if (!next.getUser().getLogin().equals(login)) iterator.remove();
        }
        return list;
    }

    @Override
    public UsersActivity setTime(UsersActivity usersActivity, Connection connection) throws SQLException {
        return dao.update(usersActivity, connection);
    }

    @Override
    public UsersActivity create(UsersActivity usersActivity, Connection connection) throws SQLException {
        return dao.create(usersActivity, connection);
    }

    @Override
    public UsersActivity update(UsersActivity usersActivity, Connection connection) throws SQLException {
        return dao.update(usersActivity, connection);
    }

    @Override
    public boolean delete(UsersActivity usersActivity, Connection connection) throws SQLException {
        return dao.delete(usersActivity, connection);
    }
}
