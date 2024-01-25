package com.example.vuttr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ToolControllerAdvice {
    @ExceptionHandler(ToolNotFoundException.class)
    public ResponseEntity<String> toolNotFoundExceptionHandler(ToolNotFoundException toolNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(toolNotFoundException.getMessage());
    }

    @ExceptionHandler(ToolAlreadyExistsException.class)
    public ResponseEntity<String> toolAlreadyExistsExceptionHandler(ToolAlreadyExistsException toolAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(toolAlreadyExistsException.getMessage());
    }
}
