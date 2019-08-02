package com.openpayd.clientstransactions.exceptionhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(OpenPaydException.class)
    protected ResponseEntity<Object> handleOpenPaydException(OpenPaydException ex) {
        ApiError apiError = new ApiError(ex.getErrorCode().getStatus());
        apiError.setMessage(ex.getErrorCode().getMessage()+" : "+ex.getMessage());
        apiError.setErrorCode(ex.getErrorCode());
        return buildResponseEntity(apiError);
    }

    //other exception handlers below

}
