package com.biblioteca.gestion_libros.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionGeneral(Exception ex, WebRequest request) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("mensaje", "Ocurrió un error inesperado");
        cuerpoError.put("detalle", ex.getMessage());
        cuerpoError.put("ruta", request.getDescription(false));
    
        return new ResponseEntity<>(cuerpoError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex, WebRequest request) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("mensaje", ex.getMessage());
        cuerpoError.put("detalle", "El recurso solicitado no existe o ha sido eliminado");
    
        return new ResponseEntity<>(cuerpoError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> manejarRutaNoEncontrada(NoHandlerFoundException ex, WebRequest request) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("mensaje", "Recurso no encontrado");
        cuerpoError.put("detalle", "La ruta solicitada no existe");
        cuerpoError.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(cuerpoError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> manejarMetodoNoSoportado(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("mensaje", "Método HTTP no permitido");
        cuerpoError.put("detalle", "El método " + ex.getMethod() + " no es soportado en esta ruta");
        cuerpoError.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(cuerpoError, HttpStatus.METHOD_NOT_ALLOWED);
    }
}