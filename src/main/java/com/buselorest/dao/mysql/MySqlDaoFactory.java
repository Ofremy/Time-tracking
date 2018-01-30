package com.buselorest.dao.mysql;

import com.buselorest.dao.interfaces.*;

public class MySqlDaoFactory implements DaoFactory {
    @Override
    public ActivityDao getActivityDao() {
        return new MySqlActivityDao();
    }

    @Override
    public RequestDao getRequestDao() {
        return new MySqlRequestDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao();
    }

    @Override
    public UsersActivityDao getUsersActivityDao() {
        return new MySqlUsersActivityDao();
    }
}
