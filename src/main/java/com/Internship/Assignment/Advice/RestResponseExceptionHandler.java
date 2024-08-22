package com.Internship.Assignment.Advice;

import com.Internship.Assignment.Exception.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorMessage> internalServerException(InternalServerException internalServerException){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, internalServerException.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlingInvalidRequests(MethodArgumentNotValidException notValidException){
        Map<String, String> exceptions = new HashMap<>();
        notValidException.getBindingResult().getFieldErrors().
                forEach(err -> exceptions.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(exceptions);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumErrors(HttpMessageNotReadableException notReadableException){
        return ResponseEntity.badRequest().body(notReadableException.getCause().getMessage());
    }
}
