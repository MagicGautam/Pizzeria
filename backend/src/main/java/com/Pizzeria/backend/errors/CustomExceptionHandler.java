package com.Pizzeria.backend.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<CustomErrorResponse> handleInsufficientStockException(InsufficientStockException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}