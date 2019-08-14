package com.bankingsystem.clientstransactions.exceptionhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(BankingSystemException.class)
    protected ResponseEntity<Object> handleBankindSystemException(BankingSystemException ex) {
        ApiError apiError = new ApiError(ex.getErrorCode().getStatus());
        apiError.setMessage(ex.getErrorCode().getMessage()+" : "+ex.getMessage());
        apiError.setErrorCode(ex.getErrorCode());
        return buildResponseEntity(apiError);
    }

    //other exception handlers below

}
