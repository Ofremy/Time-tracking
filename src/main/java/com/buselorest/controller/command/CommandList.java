package com.buselorest.controller.command;

import com.buselorest.controller.command.impl.*;
import com.buselorest.dao.connection.DBConnectionPool;
import com.buselorest.service.impl.ServiceFactoryImpl;

public enum CommandList {
    LOGIN(new LogInCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    LOGOUT(new LogOutCommand()),
    REGISTER(new RegisterCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    REQUEST_ACTIVITY(new RequestActivityCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    UPDATE_PROFILE(new UpdateProfileCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    SUBMIT_DELETE_FORM(new SubmitDeleteFormCommand()),
    SUBMIT_DELETE(new SubmitDeleteCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    ACCEPT(new AcceptCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    ACCEPT_FORM(new AcceptFormCommand()),
    DECLINE(new DeclineCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    DECLINE_FORM(new DeclineFormCommand()),
    SET_TIME(new SetTimeCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    SET_TIME_FORM(new SetTimeFormCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    PAGINATION(new PaginationCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    UPDATE(new UpdateIndexCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    REQUEST_EXIST_ACTIVITY_FORM(new RequestExistActivityFormCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    REQUEST_EXIST_ACTIVITY(new RequestExistActivityCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    CREATE_ACTIVITY_FORM(new CreateActivityFormCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    CREATE_ACTIVITY(new CreateActivityCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    CREATE_EXIST_ACTIVITY_FORM(new CreateExistActivityFormCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE)),
    CREATE_EXIST_ACTIVITY(new CreateExistActivityCommand(new ServiceFactoryImpl(), DBConnectionPool.INSTANCE));

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }
}
