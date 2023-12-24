package com.spring_basic_auth.basic_auth.exception.car_exceptions;

import com.spring_basic_auth.basic_auth.dtomodel.ErrorResponseDTO;
import com.spring_basic_auth.basic_auth.dtomodel.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCarNotFoundException(CarNotFoundException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(CarRuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleCarRuntimeException(CarRuntimeException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDTO> createErrorResponse(HttpStatus status, String message) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(status, message);
        return new ResponseEntity<>(errorResponse, status);
    }
}

