package com.buselorest.service;

import com.buselorest.dao.interfaces.ActivityDao;
import com.buselorest.dao.interfaces.UsersActivityDao;
import com.buselorest.model.domain.Activity;
import com.buselorest.service.impl.ActivityServiceImpl;
import com.buselorest.service.impl.UsersActivityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {
    @Mock
    private ActivityDao activityDao;

    @Mock
    private Connection connection;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    public void checkActivityByName() throws SQLException{
        Activity activity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();
        Activity expectedActivity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        when(activityDao.findByName("mame", connection))
                .thenReturn(activity);

        Activity actualActivity = activityService.findByName("mame", connection);

        assertEquals(expectedActivity, actualActivity);

        verify(activityDao).findByName("mame", connection);
    }

    @Test
    public void checkActivityById() throws SQLException{
        Activity activity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        Activity expectedActivity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        when(activityDao.getByPK(1,connection))
                .thenReturn(activity);

        Activity actualActivity = activityService.getByPk(1,connection);

        assertEquals(expectedActivity, actualActivity);

        verify(activityDao).getByPK(1,connection);
    }

    @Test
    public void checkCreateActivity() throws SQLException{
        Activity activity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        Activity expectedActivity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        when(activityDao.create(activity, connection))
                .thenReturn(activity);

        Activity actualActivity = activityService.save(activity, connection);

        assertEquals(expectedActivity, actualActivity);

        verify(activityDao).create(activity, connection);
    }

    @Test
    public void checkDeleteActivity() throws SQLException {
        Activity activity = new Activity.Builder()
                .id(1)
                .name("name")
                .description("description")
                .build();

        when(activityDao.delete(activity, connection))
                .thenReturn(true);

        boolean isDeleted = activityService.delete(activity, connection);

        assertTrue(isDeleted);

        verify(activityDao).delete(activity, connection);
    }

}
