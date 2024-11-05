package com.softka.prueba.ingreso.cuenta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<String> handleCustomRuntimeException(CustomRuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 

        if (ex instanceof CuentaNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } if (ex instanceof ClienteNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }if (ex instanceof ClienteServiceUnavailableException) {
            status = HttpStatus.GATEWAY_TIMEOUT;
        } else if (ex instanceof SaldoInsuficienteException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(ex.getMessage());
    }
}