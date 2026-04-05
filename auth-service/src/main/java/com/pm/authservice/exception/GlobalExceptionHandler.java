package com.pm.authservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserNameNotFoundEception(UsernameNotFoundException e)
    {
        Map<String , String> error =new HashMap<>();
        String message = e.getMessage();
        error.put("message",message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

}
