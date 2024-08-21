package com.Internship.Assignment.Advice;

import com.Internship.Assignment.Exception.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorMessage> internalServerException(InternalServerException internalServerException){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, internalServerException.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
