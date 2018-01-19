package com.buselorest.domain;

import com.buselorest.model.dao.interfaces.Identified;

import java.sql.Time;

public class UsersActivity implements Identified<Integer> {
    private Integer id;
    private int userId;
    private int activityId;
    private Time time;

    public UsersActivity() {
    }

    public UsersActivity(Integer id, int userId, int activityId, Time time, String status) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
