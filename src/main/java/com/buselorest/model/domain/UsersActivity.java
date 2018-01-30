package com.buselorest.model.domain;

public class UsersActivity extends Entity {
    public enum Status {
        PENDING,
        ACTIVE,
        END
    }
    private int id;
    private User user;
    private Activity activity;
    private String time;
    private Status status;

    public UsersActivity() {
    }

    private UsersActivity(Builder builder){
        this.id = builder.id;
        this.user = builder.user;
        this.activity = builder.activity;
        this.time = builder.time;
        this.status = builder.status;
    }

    public static class Builder {
        private int id;
        private User user;
        private Activity activity;
        private String time;
        private Status status;

        public Builder(){
        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder activity(Activity activity){
            this.activity = activity;
            return this;
        }

        public Builder time(String time){
            this.time = time;
            return this;
        }

        public Builder status(Status status){
            this.status = status;
            return this;
        }

        public UsersActivity build(){
            return new UsersActivity(this);
        }

    }

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersActivity that = (UsersActivity) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
