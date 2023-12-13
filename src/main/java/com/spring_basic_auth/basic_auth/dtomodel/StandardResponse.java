package com.spring_basic_auth.basic_auth.dtomodel;

import java.time.LocalDateTime;

public class StandardResponse<T> {
    private boolean success;
    private String timestamp;
    private int status;
    private T data;
    private String message; // Optional message for additional information

    // Constructor, getters, and setters

    public StandardResponse(boolean success,int status, T data, String message) {
        this.success = success;
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
