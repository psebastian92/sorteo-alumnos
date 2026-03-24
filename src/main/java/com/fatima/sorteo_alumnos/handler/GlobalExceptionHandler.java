package com.fatima.sorteo_alumnos.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.orm.jpa.JpaSystemException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseDown(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Servicio temporalmente no disponible");
        body.put("mensaje", "No se pudo conectar con la base de datos");
        
        // Devolvemos un 503 (Service Unavailable) en lugar de un 500
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
