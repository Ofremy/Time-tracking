package com.buselorest.service.impl;

import com.buselorest.model.domain.Activity;
import com.buselorest.dao.interfaces.ActivityDao;
import com.buselorest.service.ActivityService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao dao;

    public ActivityServiceImpl(ActivityDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Activity> getAll(Connection connection) throws SQLException {
        return dao.getAll(connection);
    }

    @Override
    public Activity getByPk(int key, Connection connection) throws SQLException {
        return dao.getByPK(key, connection);
    }

    @Override
    public Activity save(Activity activity, Connection connection) throws SQLException {
        return dao.create(activity, connection);
    }

    @Override
    public Activity findByName(String name, Connection connection) throws SQLException {
        return dao.findByName(name, connection);
    }

    @Override
    public boolean delete(Activity activity, Connection connection) throws SQLException {
        return dao.delete(activity, connection);
    }
}
