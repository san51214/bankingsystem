package com.openpayd.clientstransactions.exceptionhandler;

import ch.qos.logback.core.util.TimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.openpayd.clientstransactions.utils.DateTimeUtil;
import lombok.Data;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String message;
    private String debugMessage;
    ErrorCode errorCode;


    private ApiError() {
        timestamp = DateTimeUtil.getNow();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(ErrorCode errorCode, String message, Throwable ex) {
        this();
        this.status = errorCode.getStatus();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        this.errorCode = errorCode;
    }
}
