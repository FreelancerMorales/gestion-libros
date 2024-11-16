package com.biblioteca.gestion_libros.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TiempoEjercucionInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("tiempoInicio", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long tiempoInicio = (long) request.getAttribute("tiempoInicio");
        long tiempoEjecucion = System.currentTimeMillis() - tiempoInicio;

        System.out.println("\nSolicitud a " + request.getRequestURI() + " completada en " + tiempoEjecucion + " ms");
    }
}
