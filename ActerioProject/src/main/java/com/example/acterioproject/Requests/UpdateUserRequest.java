package com.example.acterioproject.Requests;

//Created for the PUT request, BODY

public class UpdateUserRequest {
    private String oldPassword;
    private String newEmail;
    private String newPassword;

    // Constructor, getters, and setters
    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String oldPassword, String newEmail, String newPassword) {
        this.oldPassword = oldPassword;
        this.newEmail = newEmail;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
