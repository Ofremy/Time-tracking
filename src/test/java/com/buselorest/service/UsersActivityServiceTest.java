package com.buselorest.service;

import com.buselorest.dao.interfaces.DaoFactory;
import com.buselorest.dao.interfaces.UsersActivityDao;
import com.buselorest.model.domain.Activity;
import com.buselorest.model.domain.User;
import com.buselorest.model.domain.UsersActivity;
import com.buselorest.service.impl.UsersActivityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class UsersActivityServiceTest {
    @Mock
    private UsersActivityDao usersActivityDao;

    @Mock
    private Connection connection;

    @InjectMocks
    private UsersActivityServiceImpl usersActivityService;


    @Test
    public void checkExistUsersActivity() throws SQLException{
        List<UsersActivity> usersActivities = Arrays.asList(
          new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build(),
          new UsersActivity.Builder()
                .id(2)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("04:05:06")
                .status(UsersActivity.Status.ACTIVE)
                .build()
        );

        List<UsersActivity> expectedUsersActivity = new ArrayList<>();
        expectedUsersActivity.addAll(usersActivities);


        when(usersActivityDao.getAll(connection))
                .thenReturn(usersActivities);

        List<UsersActivity> currentUsersActivity = usersActivityService.getAllByLogin("fremy", connection);

        assertEquals(expectedUsersActivity, currentUsersActivity);

        verify(usersActivityDao).getAll(connection);
    }

    @Test
    public void checkCreateUsersActivity() throws SQLException{
        UsersActivity usersActivity = new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build();

        UsersActivity usersActivityClone = new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build();

        when(usersActivityDao.create(usersActivity,connection))
                .thenReturn(usersActivity);

        UsersActivity actualUsersActivity = usersActivityService.create(usersActivity, connection);

        assertEquals(usersActivityClone, actualUsersActivity);

        verify(usersActivityDao).create(usersActivity, connection);
    }

    @Test
    public void checkDeleteUsersActivity() throws SQLException{
        UsersActivity usersActivity = new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build();

        when(usersActivityDao.delete(usersActivity,connection))
                .thenReturn(true);

        boolean isDelete = usersActivityService.delete(usersActivity,connection);

        assertTrue(isDelete);

        verify(usersActivityDao).delete(usersActivity, connection);
    }

    @Test
    public void checkSetTimeForUserActivity() throws SQLException{
        UsersActivity usersActivity = new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build();

        UsersActivity expectedUserActivity = new UsersActivity.Builder()
                .id(1)
                .user(new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build())
                .activity(new Activity.Builder().name("activity").description("description").build())
                .time("03:04:05")
                .status(UsersActivity.Status.ACTIVE)
                .build();

        when(usersActivityDao.update(usersActivity, connection))
                .thenReturn(usersActivity);

        UsersActivity actualUserActivity = usersActivityService.setTime(usersActivity, connection);

        assertEquals(expectedUserActivity, actualUserActivity);

        verify(usersActivityDao).update(usersActivity,connection);
    }
}
