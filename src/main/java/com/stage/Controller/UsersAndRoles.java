package com.stage.Controller;

import java.util.List;

public class UsersAndRoles {
    private List<User> user;
    private List<Role> role;

    public void setUser(List<User> user) {
        this.user = user;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<User> getUser() {
        return user;
    }

    public List<Role> getRole() {
        return role;
    }
}
