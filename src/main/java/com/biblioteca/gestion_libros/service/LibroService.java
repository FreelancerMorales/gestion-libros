package com.biblioteca.gestion_libros.service;

import java.util.List;
import java.util.Optional;

import com.biblioteca.gestion_libros.model.LibroModel;

public interface LibroService {
    LibroModel guardarLibroRepository(LibroModel libro );

    List<LibroModel> obtenerLibrosRepository();

    Optional<LibroModel> obtenerLibroRepositoryPorId(Long id);

    LibroModel actualizarLibroRepository(Long id, LibroModel libroDetalles);

    void eliminarLibroRepository(Long id);
}
