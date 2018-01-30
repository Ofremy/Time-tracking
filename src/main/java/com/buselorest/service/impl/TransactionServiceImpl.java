package com.buselorest.service.impl;

import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public boolean saveActivityForUserTransact(ServiceFactory serviceFactory,
                                               Connection connection,
                                               UsersActivity usersActivity) throws SQLException {
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        ActivityService activityService = serviceFactory.getActivityService();
        try {
            connection.setAutoCommit(false);
            boolean usersActivityPresent = false;
            List<UsersActivity> list = usersActivityService.
                    getAllByLogin(usersActivity.getUser().getLogin(), connection);
            for (UsersActivity activity : list) {
                if (activity.getActivity().getId() == usersActivity.getActivity().getId()) {
                    usersActivityPresent = true;
                    break;
                }
            }
            if (!usersActivityPresent) {
                boolean activityIsPresent = false;
                for (Activity activity : activityService.getAll(connection)) {
                    if (activity.getName().equals(usersActivity.getActivity().getName())) {
                        activityIsPresent = true;
                        break;
                    }
                }
                if (!activityIsPresent) {
                    usersActivity.setActivity(activityService.save(usersActivity.getActivity(), connection));
                }
                usersActivityService.create(usersActivity, connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw e;
            }
            throw e;
        }
        return true;
    }


    @Override
    public boolean deleteRequestSetActivityForUserTransact(ServiceFactory serviceFactory,
                                                           Connection connection,
                                                           Request request,
                                                           UsersActivity usersActivity) throws SQLException {
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        RequestService requestService = serviceFactory.getRequestService();
        ActivityService activityService = serviceFactory.getActivityService();
        try {
            connection.setAutoCommit(false);
            requestService.delete(request, connection);
            switch (request.getStatus()) {
                case DELETE:
                    usersActivityService.delete(usersActivity, connection);
                    break;
                case ADD:
                    boolean usersActivityPresent = false;
                    List<UsersActivity> list = usersActivityService.
                            getAllByLogin(usersActivity.getUser().getLogin(), connection);
                    for (UsersActivity activity : list) {
                        if (activity.getActivity().getId() == usersActivity.getActivity().getId()) {
                            usersActivityPresent = true;
                            break;
                        }
                    }
                    if (!usersActivityPresent) {
                        boolean activityIsPresent = false;
                        for (Activity activity : activityService.getAll(connection)) {
                            if (activity.getName().equals(usersActivity.getActivity().getName())) {
                                activityIsPresent = true;
                                break;
                            }
                        }
                        if (!activityIsPresent) {
                            usersActivity.setActivity(activityService.save(usersActivity.getActivity(), connection));
                        }
                        usersActivityService.create(usersActivity, connection);
                        break;
                    }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw e;
            }
            throw e;
        }
        return true;
    }



      @Override
      public void userActivityUpdateRequestCreate(ServiceFactory serviceFactory,
                                                  Connection connection,
                                                  UsersActivity usersActivity,
                                                  Request request) {
          UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
          RequestService requestService = serviceFactory.getRequestService();
          try {
              connection.setAutoCommit(false);
              usersActivityService.update(usersActivity, connection);
              requestService.create(request, connection);
              connection.commit();
              connection.setAutoCommit(true);
          } catch (SQLException e) {
              try {
                  connection.rollback();
              } catch (SQLException e1) {
                  e1.printStackTrace();
              }
              e.printStackTrace();
          }
      }

    @Override
    public boolean addOrDeleteRequestSetUserForActivityTransact(ServiceFactory serviceFactory,
                                                                Connection connection,
                                                                UsersActivity usersActivity,
                                                                Request request) throws SQLException {
        UsersActivityService usersActivityService = serviceFactory.getUsersActivityService();
        RequestService requestService = serviceFactory.getRequestService();
        try {
            connection.setAutoCommit(false);
            switch (request.getStatus()) {
                case ADD:
                    break;
                case DELETE:
                    requestService.delete(request, connection);
                    break;
            }
            usersActivityService.update(usersActivity, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw e;
            }
            throw e;
        }
        return true;
    }
}
