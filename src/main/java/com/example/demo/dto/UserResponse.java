package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserResponse {

    private int status;
    private String error;
    private String message;
    private User data;

    // Constructor
    public UserResponse(int status, String error, String message, User data) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    
}
