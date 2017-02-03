package com.stage.Controller;

import java.io.Serializable;

/**
 * Created by wital on 30.01.2017.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String idrole;

    public User() {
    }

    public User(String id, String name, String email, String idrole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idrole = idrole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdrole(String idrole) {
        this.idrole = idrole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getIdrole() {
        return idrole;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
