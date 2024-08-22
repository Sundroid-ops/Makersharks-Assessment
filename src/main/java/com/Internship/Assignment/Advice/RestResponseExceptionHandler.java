package com.Internship.Assignment.Advice;

import com.Internship.Assignment.Exception.InternalServerException;
import com.Internship.Assignment.Exception.SupplierNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    private static final Logger logs = LoggerFactory.getLogger(RestResponseExceptionHandler.class);
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorMessage> internalServerException(InternalServerException internalServerException){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, internalServerException.getMessage());
        logs.error("Internal Server Error: {}", error);
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlingInvalidRequests(MethodArgumentNotValidException notValidException){
        Map<String, String> exceptions = new HashMap<>();
        notValidException.getBindingResult().getFieldErrors().
                forEach(err -> exceptions.put(err.getField(), err.getDefaultMessage()));

        logs.warn("Invalid Incoming Requests : {}", exceptions);
        return ResponseEntity.badRequest().body(exceptions);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumErrors(HttpMessageNotReadableException notReadableException){
        logs.warn("Invalid Enums Type : {}", notReadableException.getMessage());
        return ResponseEntity.badRequest().body(notReadableException.getCause().getMessage());
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ErrorMessage> supplierNotFoundException(SupplierNotFoundException notFoundException){
        ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        logs.warn("Supplier Data not found : {}", notFoundException.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException mismatchException){
        logs.warn("specify data more clearly for : {}", mismatchException.getPropertyName());
        ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST, mismatchException.getMessage());
        return ResponseEntity.badRequest().body(err);
    }
}
