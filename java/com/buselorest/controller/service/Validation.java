package com.buselorest.controller.service;

import com.buselorest.domain.User;
import com.buselorest.model.dao.interfaces.DaoFactory;
import com.buselorest.model.dao.interfaces.GenericDao;
import com.buselorest.model.dao.mysql.MySqlDaoFactory;
import com.buselorest.model.exception.PersistException;

import java.sql.Connection;
import java.util.List;

public class Validation {
    private static GenericDao<User, Integer> dao;
    static {
        try {
            DaoFactory<Connection> daoFactory = new MySqlDaoFactory();
            Connection connection = daoFactory.getContext();
            dao = daoFactory.getDao(connection, User.class);
        } catch (PersistException e) {
            e.printStackTrace();// tyt
        }
    }

    public static boolean isLoginExisted(String login) throws PersistException {
        List<User> list = dao.getAll();
        for (User user : list) {
           // System.out.println(user.getLogin() +" = " + login);
            if (user.getLogin().equals(login)) return true;
        }
        return false;
    }

    public static boolean isPasswordMatches(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static User getUser(String login, String password) throws PersistException{
        List<User> list = dao.getAll();
        for (User u : list) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)){
                return new User(u.getLogin(), u.getPassword(), u.getRole());
            }
        }
        return null;
    }


}
