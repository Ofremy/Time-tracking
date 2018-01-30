package com.buselorest.service.impl;

import com.buselorest.controller.constants.MessageConstants;
import com.buselorest.exception.WrongInputException;
import com.buselorest.model.domain.User;
import com.buselorest.dao.interfaces.UserDao;
import com.buselorest.service.ResourceManager;
import com.buselorest.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao;
    private final static String NAME_MATCHER = "[A-Za-zА-Яа-я]*";
    private ResourceManager instance = ResourceManager.INSTANCE;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User getUserByLogin(String login, Connection connection) throws SQLException {
        return dao.findByLogin(login, connection);
    }

    @Override
    public User login(User user, Connection connection) throws WrongInputException, SQLException {
        String login = user.getLogin();
        String password = user.getPassword();
        user = dao.findByLogin(login,connection);
        if (user != null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User register(User user, Connection connection) throws WrongInputException, SQLException {
        String login = user.getLogin();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (dao.findByLogin(login, connection) != null) {
            throw new WrongInputException(instance.getString(MessageConstants.USER_EXIST));
        }
        if (firstName == null || !firstName.matches(NAME_MATCHER)) {
            throw new WrongInputException(instance.getString(MessageConstants.FIRST_NAME_INVALID));
        }
        if (lastName == null || !lastName.matches(NAME_MATCHER)) {
            throw new WrongInputException(instance.getString(MessageConstants.LAST_NAME_INVALID));
        }
        return dao.create(user, connection);
    }

    @Override
    public User update(User user, Connection connection) throws WrongInputException, SQLException {
        String login = user.getLogin();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (login == null || login.isEmpty()) {
            throw new WrongInputException(instance.getString(MessageConstants.LOGIN_INVALID));
        }
        if (firstName == null || firstName.isEmpty() || !firstName.matches(NAME_MATCHER)) {
            throw new WrongInputException(instance.getString(MessageConstants.FIRST_NAME_INVALID));
        }
        if (lastName == null || lastName.isEmpty() || !lastName.matches(NAME_MATCHER)) {
            throw new WrongInputException(instance.getString(MessageConstants.LAST_NAME_INVALID));
        }
        return dao.update(user, connection);
    }

    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        return dao.getAll(connection);
    }
}
