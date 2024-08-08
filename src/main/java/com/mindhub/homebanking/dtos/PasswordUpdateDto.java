package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.PasswordUpdate;

public class PasswordUpdateDto {
    private long id;
    private String password;
    private String oldPassword;
    private String newPassword;

    public PasswordUpdateDto(PasswordUpdate passwordUpdate) {
        this.id = passwordUpdate.getId();
        this.password = passwordUpdate.getPassword();
        this.oldPassword = passwordUpdate.getOldPassword();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
