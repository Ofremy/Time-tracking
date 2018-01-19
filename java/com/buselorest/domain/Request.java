package com.buselorest.domain;

import com.buselorest.model.dao.interfaces.Identified;

public class Request implements Identified<Integer> {
    public enum Status{
        ACCEPT,
        DECLINE,
        PROCESSING
    }
    private Integer id;
    private int userId;
    private int activityId;// name
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Request() {
    }

    public Request(Integer id, int userId, int activityId, Status status) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.status = status;
    }

    public int getActivityId() {

        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
