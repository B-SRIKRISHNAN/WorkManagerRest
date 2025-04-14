package com.workmanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// import jakarta.persistence.EntityNotFoundException;
import com.workmanager.model.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(EntityNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //             .body(ex.getMessage());
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setErrors(ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList()));
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiError.setPath(request.getRequestURI()); // Set the actual request path
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleGeneralException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setErrors(List.of(ex.getMessage()));
        apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setPath(request.getRequestURI()); // Set the actual request path
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }
}
