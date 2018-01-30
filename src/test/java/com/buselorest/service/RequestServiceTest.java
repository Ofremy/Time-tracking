package com.buselorest.service;

import com.buselorest.dao.interfaces.ActivityDao;
import com.buselorest.dao.interfaces.RequestDao;
import com.buselorest.model.domain.Request;
import com.buselorest.model.domain.User;
import com.buselorest.service.impl.ActivityServiceImpl;
import com.buselorest.service.impl.RequestServiceImpl;
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
public class RequestServiceTest {
    @Mock
    private RequestDao requestDao;

    @Mock
    private Connection connection;

    @InjectMocks
    private RequestServiceImpl requestService;

    @Test
    public void checkCreateRequest() throws SQLException {
        Request request = new Request(1,
                new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build(),
                "activityName",
                "description",
                Request.Status.ADD);

        Request expectedRequest = new Request(1,
                new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build(),
                "activityName",
                "description",
                Request.Status.ADD);

        when(requestDao.create(request, connection))
                .thenReturn(request);

        Request actualRequest = requestService.create(request, connection);

        assertEquals(expectedRequest, actualRequest);

        verify(requestDao).create(request, connection);
    }

    @Test
    public void testDeleteRequest() throws SQLException {
        Request request = new Request(1,
                new User.Builder()
                        .id(1)
                        .login("fremy")
                        .firstName("name")
                        .lastName("lastName")
                        .password("123")
                        .build(),
                "activityName",
                "description",
                Request.Status.ADD);

        when(requestDao.delete(request, connection))
                .thenReturn(true);

        boolean isDeleted = requestService.delete(request, connection);

        assertTrue(isDeleted);

        verify(requestDao).delete(request, connection);
    }

    @Test
    public void testGetAllRequest() throws SQLException{
        List<Request> list = Arrays.asList(
                new Request(1,
                        new User.Builder()
                                .id(1)
                                .login("fremy")
                                .firstName("name")
                                .lastName("lastName")

                                .password("123")
                                .build(),
                        "activityName",
                        "description",
                        Request.Status.ADD),
                new Request(2,
                        new User.Builder()
                                .id(1)
                                .login("fremy")
                                .firstName("zzz")
                                .lastName("lastName")
                                .password("123")
                                .build(),
                        "yry",
                        "description",
                        Request.Status.ADD),
                new Request(3,
                        new User.Builder()
                                .id(1)
                                .login("fremy")
                                .firstName("name")
                                .lastName("lastName")
                                .password("123")
                                .build(),
                        "fw",
                        "description",
                        Request.Status.ADD)
        );

        List<Request> expectedList = new ArrayList<>();
        expectedList.addAll(list);

        when(requestDao.getAll(connection))
                .thenReturn(list);

        List<Request> actualLit = requestService.getAll(connection);

        assertEquals(expectedList, actualLit);

        verify(requestDao).getAll(connection);
    }
}
