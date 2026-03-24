package com.fatima.sorteo_alumnos.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1. ERROR CRÍTICO DE CONEXIÓN (BD Apagada, Timeout de Hikari, JPA muerto)
     * Este atrapa el error de "Unable to acquire JDBC Connection" que recibiste.
     */
    /**
     * 1. ERROR CRÍTICO DE CONEXIÓN (BD Apagada, Timeout de Hikari, JPA muerto)
     */
    @ExceptionHandler({
        org.springframework.orm.jpa.JpaSystemException.class, 
        org.springframework.transaction.CannotCreateTransactionException.class,
        org.springframework.dao.DataAccessResourceFailureException.class,
        org.springframework.transaction.TransactionSystemException.class, // <--- AGREGA ESTA
        org.hibernate.exception.JDBCConnectionException.class,           // <--- AGREGA ESTA
        jakarta.persistence.PersistenceException.class
    })
    public ResponseEntity<Map<String, Object>> handleDatabaseDown(Exception ex) {
        // Log para ver qué está pasando realmente en la consola
        System.out.println("Excepción capturada: " + ex.getClass().getName());
        
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Servicio temporalmente no disponible");
        body.put("mensaje", "La base de datos no responde (Timeout de conexión)");
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }


    /**
     * 2. RECURSO NO ENCONTRADO (404)
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoResourceFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Recurso no encontrado");
        body.put("mensaje", "La ruta solicitada no existe");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * 3. ERROR DE PARÁMETROS O FORMATO (400)
     */
    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Solicitud incorrecta");
        body.put("mensaje", "El formato de los datos enviados es inválido");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * 4. ERROR GENÉRICO (Atrapa todo lo demás)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Error interno");
        body.put("mensaje", "Ocurrió un error inesperado: " + ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
