package com.sa.clothingstore.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknow error");
//    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(Exception ex) {
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
