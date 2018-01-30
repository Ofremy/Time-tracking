package com.buselorest.service;

import com.buselorest.dao.interfaces.AbstractJDBCDao;
import com.buselorest.dao.interfaces.DaoFactory;
import com.buselorest.dao.interfaces.UserDao;
import com.buselorest.exception.WrongInputException;
import com.buselorest.model.domain.User;
import com.buselorest.service.UserService;
import com.buselorest.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private Connection connection;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void checkExistingUser() throws SQLException {
        User user = new User.Builder()
                .id(1)
                .login("fremy")
                .firstName("name")
                .lastName("lastName")
                .password("123")
                .build();

        User userClone = new User.Builder()
                .id(1)
                .login("fremy")
                .firstName("name")
                .lastName("lastName")
                .password("123")
                .build();

        when(userDao.findByLogin("fremy", connection))
                .thenReturn(user);

        User actualUser = userService.getUserByLogin("fremy", connection);

        Assert.assertEquals(userClone, actualUser);

        verify(userDao).findByLogin("fremy", connection);
    }

    @Test
    public void checkNonExistUser() throws SQLException {
        User userActual = userService.getUserByLogin("fremy", connection);

        Assert.assertNull(userActual);
    }

    @Test(expected = NullPointerException.class)
    public void checkNullLogin() throws SQLException {

        when(userDao.findByLogin(null, connection)).thenThrow(new NullPointerException());

        userService.getUserByLogin(null, connection);
    }

    @Test
    public void checkReturnList() throws SQLException {
        List<User> users = new ArrayList<>();

        users.add(new User.Builder()
                .id(1)
                .login("fremy")
                .firstName("name")
                .lastName("lastName")
                .password("123")
                .build());
        users.add(new User.Builder()
                .id(1)
                .login("orest")
                .firstName("name2")
                .lastName("lastName2")
                .password("131")
                .build());
        users.add(new User.Builder()
                .id(1)
                .login("evgen")
                .firstName("gena")
                .lastName("last")
                .password("433")
                .build());

        List<User> expectUser = new ArrayList<>();
        expectUser.addAll(users);


        when(userDao.getAll(connection)).thenReturn(users);

        List<User> actualUsers = userService.getAll(connection);

        assertEquals(expectUser, actualUsers);

        verify(userDao).getAll(connection);
    }

    @Test
    public void checkRegisterUser() throws WrongInputException, SQLException {
        User user = new User.Builder()
                .id(1)
                .login("fremy")
                .firstName("name")
                .lastName("lastName")
                .password("123")
                .build();

        User expectedUser = new User.Builder()
                .id(1)
                .login("fremy")
                .firstName("name")
                .lastName("lastName")
                .password("123")
                .build();

        when(userDao.create(user,connection))
                .thenReturn(user);

        User actualUser = userService.register(user, connection);

        assertEquals(expectedUser, actualUser);

        verify(userDao).create(expectedUser, connection);
    }

}
