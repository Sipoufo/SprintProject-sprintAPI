package com.sprint.sprintAPI.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MobileExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Exception liée aux devices
    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDeviceNotFoundException(DeviceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Exception liée aux type de devices
    @ExceptionHandler(DeviceTypeException.class)
    public ResponseEntity<ErrorResponse> handleDeviceTypeException(DeviceTypeException ex) {
        int statusCode;
        HttpStatus httpStatus;
        
        if (ex.getMessage().startsWith("Type d'appareil introuvable")) {
            statusCode = HttpStatus.NOT_FOUND.value();
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            statusCode = HttpStatus.BAD_REQUEST.value();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        
        ErrorResponse errorResponse = new ErrorResponse(statusCode, ex.getMessage());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    // Exception liée aux utilisateurs
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        int statusCode;
        HttpStatus httpStatus;

        if (ex == UserException.userNotFound()) {
            statusCode = HttpStatus.NOT_FOUND.value();
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex == UserException.invalidUser()) {
            statusCode = HttpStatus.BAD_REQUEST.value();
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            // Si une autre exception de type UserException est levée, on peut renvoyer une erreur générique
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse errorResponse = new ErrorResponse(statusCode, ex.getMessage());

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    // Exception liée aux metriques
    @ExceptionHandler(MetricException.class)
    public ResponseEntity<ErrorResponse> handleMetricException(MetricException ex) {
        HttpStatus httpStatus;
        if (ex == MetricException.metricNotFound()) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex == MetricException.invalidMetric()) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
