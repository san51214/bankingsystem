package com.bankingsystem.clientstransactions.exceptionhandler;

import lombok.Data;

@Data
public class BankingSystemException extends RuntimeException {

    ErrorCode errorCode;
    String message;
    public BankingSystemException(String message, ErrorCode errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
