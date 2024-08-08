package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Role;

public class RoleDto {
    private long id;
    private String roles;

    public RoleDto(Role role) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
