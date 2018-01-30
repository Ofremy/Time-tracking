package com.buselorest.dao.interfaces;

/**
 * Interface for create dao Instance
 */
public interface DaoFactory {
    /**
     * Create instance of ActivityDao
     * @return {@link ActivityDao}
     */
    ActivityDao getActivityDao();

    /**
     * Create instance of RequestDao
     * @return {@link RequestDao}
     */
    RequestDao getRequestDao();

    /**
     * Create instance of UserDao
     * @return {@link UserDao}
     */
    UserDao getUserDao();

    /**
     * Create instance of UsersActivityDao
     * @return {@link UsersActivityDao}
     */
    UsersActivityDao getUsersActivityDao();

}
