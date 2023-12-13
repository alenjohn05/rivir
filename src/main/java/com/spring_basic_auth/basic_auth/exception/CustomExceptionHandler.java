package com.spring_basic_auth.basic_auth.exception;

import com.spring_basic_auth.basic_auth.dtomodel.StandardResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler {
    /**
     * Data Access Exceptions:
     * DataAccessException: Generic data access exception thrown by data access classes.
     * DataIntegrityViolationException: Indicates a data integrity violation such as a constraint violation.
     */

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public StandardResponse<Object> handleDataAccessException(DataAccessException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponse<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Validation and Input Errors:
     * MethodArgumentNotValidException: Raised when validation on an argument annotated with @Valid fails.
     * ConstraintViolationException: Result of failed bean validation.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new StandardResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                errors,
                "Validation error: Check the request body for details"
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardResponse<Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return new StandardResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                errors,
                "Constraint violation: Check the request for details"
        );
    }

    /**
     * Resource Not Found:
     * NoSuchElementException: Thrown when attempting to access a non-existent element.
     * EntityNotFoundException: Indicates that an entity does not exist in the database.
     */

    @ExceptionHandler({ EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardResponse<Object> handleResourceNotFoundExceptions(Exception ex) {
        return new StandardResponse<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                null,
                ex.getMessage()
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public StandardResponse<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Security Exceptions:
     * AccessDeniedException: Access denied by the security system.
     * AuthenticationException: Generic authentication exception.
     */

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public StandardResponse<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return createErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public StandardResponse<Object> handleAuthenticationException(AuthenticationException ex) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    // HTTP Request Handling
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public StandardResponse<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public StandardResponse<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return createErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
    }

    // General Application Errors
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardResponse<Object> handleIllegalStateOrArgument(Exception ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardResponse<Object> handleNullPointerException(NullPointerException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    // Concurrency and Thread Safety
    @ExceptionHandler({ConcurrentModificationException.class, InterruptedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public StandardResponse<Object> handleConcurrencyIssues(Exception ex) {
        return createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // File and I/O Handling
    @ExceptionHandler({IOException.class, FileNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardResponse<Object> handleIOException(Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    // Service Communication Errors
    @ExceptionHandler({RestClientException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardResponse<Object> handleRestClientException(Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardResponse<Object> handleAllException(Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }


    private StandardResponse<Object> createErrorResponse(HttpStatus status, String message) {
        return new StandardResponse<>(
                false,
                status.value(),
                null,
                message
        );
    }
}

