package com.buselorest.model.domain;

public class Request extends Entity{
    public enum Status{
        ADD,
        DELETE
    }
    private int id;
    private User user;
    private String activityName;
    private String description;
    private Status status;


    public void setStatus(Status status) {
        this.status = status;
    }

    public Request() {
    }

    public Request(int id, User user, String activityName, String description, Status status) {
        this.id = id;
        this.user = user;
        this.activityName = activityName;
        this.description = description;
        this.status = status;
    }

    public Request(User user, String activityName, String description, Status status) {
        this.user = user;
        this.activityName = activityName;
        this.description = description;
        this.status = status;
    }

    public String getActivityName() {

        return activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (user != null ? !user.equals(request.user) : request.user != null) return false;
        if (activityName != null ? !activityName.equals(request.activityName) : request.activityName != null)
            return false;
        if (description != null ? !description.equals(request.description) : request.description != null) return false;
        return status == request.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (activityName != null ? activityName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
