package com.buselorest.service;

public interface ServiceFactory {
    /**
     * Create Instance of RequestService
     *
     * @return - {@link RequestService}
     */
    RequestService getRequestService();

    /**
     * Create Instance of ActivityService
     *
     * @return - {@link ActivityService}
     */
    ActivityService getActivityService();

    /**
     * Create Instance of UsersActivityService
     *
     * @return - {@link UsersActivityService}
     */
    UsersActivityService getUsersActivityService();

    /**
     * Create Instance of UserService
     *
     * @return - {@link UserService}
     */
    UserService getUserService();

    /**
     * Create Instance of TransactionService
     *
     * @return - {@link TransactionService}
     */
    TransactionService geTransactionService();
}
