package com.bankingsystem.clientstransactions.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    NOT_FOUND(1002, "No found", HttpStatus.NOT_FOUND),
    ALREADY_EXIST(1003,"Already exist",HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatus status)
    {
        this.setCode(code);
        this.setMessage(message);
        this.setStatus(status);
    }

    private int code;
    private String message;
    private HttpStatus status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
