package com.sa.clothingstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknow error");
//    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lack of information");
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ObjectNotFoundException objectNotFoundException){
        return new ResponseEntity<>(objectNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<String> handleNotFoundException(ObjectAlreadyExistsException objectAlreadyExistsException){
        return new ResponseEntity<>(objectAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
