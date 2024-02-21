package com.example.acterioproject.Requests;

//Created for the PATCH request, BODY

public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;

    // Constructor, getters, and setters
    public UpdatePasswordRequest() {

    }

    public UpdatePasswordRequest(String oldPassword, String newPassword) {
        System.out.println(oldPassword);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

