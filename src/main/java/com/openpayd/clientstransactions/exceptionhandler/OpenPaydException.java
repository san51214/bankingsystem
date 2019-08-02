package com.openpayd.clientstransactions.exceptionhandler;

import lombok.Data;

@Data
public class OpenPaydException extends RuntimeException {

    ErrorCode errorCode;
    String message;
    public OpenPaydException(String message, ErrorCode errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
