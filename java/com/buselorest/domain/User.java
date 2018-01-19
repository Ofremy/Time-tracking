package com.buselorest.domain;

import com.buselorest.model.dao.interfaces.Identified;

public class User implements Identified<Integer>{
    public enum Role{
        ADMIN,
        USER
    }
    private Integer id;
    private String login;
    private String password;
    private Role role;

    public User(){

    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
