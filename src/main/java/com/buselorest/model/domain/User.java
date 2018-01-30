package com.buselorest.model.domain;

public class User extends Entity {
    public enum Role{
        ADMIN,
        USER
    }
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public User(){

    }

    private User(Builder userBuilder){
        this.id = userBuilder.id;
        this.login = userBuilder.login;
        this.firstName = userBuilder.fistName;
        this.lastName = userBuilder.lastName;
        this.password = userBuilder.password;
        this.role = Role.USER;
    }

    public static class Builder {
        private int id;
        private String login;
        private String fistName;
        private String lastName;
        private String password;

        public Builder(){

        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder login(String login){
            this.login = login;
            return this;
        }

        public Builder firstName(String fistName){
            this.fistName = fistName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

}
