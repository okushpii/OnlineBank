package com.alexbro.onlinebank.core.entity;

import com.alexbro.onlinebank.core.entity.common.IndexedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Principal extends IndexedEntity {

    @Column
    private Role role;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
