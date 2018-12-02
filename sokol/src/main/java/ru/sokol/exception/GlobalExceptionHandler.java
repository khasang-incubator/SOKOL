package ru.sokol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mikhail Bedritskiy
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiExceptionDto errorDto = new ApiExceptionDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(EnumEntityNotFoundException.class)
//    public ResponseEntity<ApiExceptionDto> handleEnumEntityNotFoundException(EnumEntityNotFoundException ex) {
//        ApiExceptionDto errorDto = new ApiExceptionDto(ex.getMessage());
//        return new ResponseEntity<>(errorDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ApiExceptionDto errorDto = new ApiExceptionDto(errors);
        return new ResponseEntity<>(errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiExceptionDto> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String cause = ex.getCause().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String error = String.format("%s in the request '%s %s'", cause, method, uri);
        ApiExceptionDto errorDto = new ApiExceptionDto(error);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto errorDto = new ApiExceptionDto(ex.getMessage());
        return handleExceptionInternal(ex, errorDto, headers, status, request);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiExceptionDto errorDto = new ApiExceptionDto(errors);
        return handleExceptionInternal(ex, errorDto, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Getter
    @AllArgsConstructor
    private static class ApiExceptionDto {

        private final List<String> errors;

        ApiExceptionDto(String message) {
            this(Collections.singletonList(message));
        }
    }
}
