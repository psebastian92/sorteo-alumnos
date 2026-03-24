package com.fatima.sorteo_alumnos.handler;

import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Servicio temporalmente no disponible");
        body.put("mensaje", "No se pudo conectar con la base de datos (Error de Sistema)");
        
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
