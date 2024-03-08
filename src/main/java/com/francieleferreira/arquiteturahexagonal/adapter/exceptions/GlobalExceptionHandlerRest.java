package com.francieleferreira.arquiteturahexagonal.adapter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandlerRest {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(RuntimeException exception) {
        var response = new HashMap<>();
        response.put("status:", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error:", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.put("message:", exception.getMessage());
        response.put("timestamp:", LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
