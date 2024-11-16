package com.biblioteca.gestion_libros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gestion_libros.model.LibroModel;
import com.biblioteca.gestion_libros.service.LibroService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroModel> crearLibro(@RequestBody LibroModel libroModel) {
        return ResponseEntity.ok(libroService.guardarLibroRepository(libroModel));
    }

    @GetMapping
    public ResponseEntity<List<LibroModel>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerLibrosRepository());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroModel> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroRepositoryPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroModel> actualizarLibro(@PathVariable Long id, @RequestBody LibroModel libroDetalles) {
        try {
            return ResponseEntity.ok(libroService.actualizarLibroRepository(id, libroDetalles));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibroRepository(id);
        return ResponseEntity.noContent().build();
    }
}
