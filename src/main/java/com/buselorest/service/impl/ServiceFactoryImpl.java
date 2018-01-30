package com.buselorest.service.impl;

import com.buselorest.dao.mysql.MySqlDaoFactory;
import com.buselorest.service.*;

public class ServiceFactoryImpl implements ServiceFactory {
    @Override
    public RequestService getRequestService() {
        return new RequestServiceImpl(new MySqlDaoFactory().getRequestDao());
    }

    @Override
    public ActivityService getActivityService() {
        return new ActivityServiceImpl(new MySqlDaoFactory().getActivityDao());
    }

    @Override
    public UsersActivityService getUsersActivityService() {
        return new UsersActivityServiceImpl(new MySqlDaoFactory().getUsersActivityDao());
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl(new MySqlDaoFactory().getUserDao());
    }

    @Override
    public TransactionService geTransactionService() {
        return new TransactionServiceImpl();
    }

}
