package com.example.acterioproject.Requests;

//Created for the DELETE request, BODY
public class DeleteRequest {
    private String password;

    public DeleteRequest() {

    }

    public DeleteRequest(String oldPassword) {

        this.password = oldPassword;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

