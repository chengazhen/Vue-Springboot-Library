package com.example.demo.config;

import com.example.demo.commom.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return Result.error("400",errorMessage);
    }

    @ExceptionHandler(value = SQLException.class)
    public Result sqlExceptionHandler(SQLException exception){
        return Result.error("500",exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result defaultExceptionHandler(Exception exception){
        String message = exception.getMessage();
        return Result.error("0",exception.getMessage());
    }
}
