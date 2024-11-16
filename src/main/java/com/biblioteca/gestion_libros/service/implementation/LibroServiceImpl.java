package com.biblioteca.gestion_libros.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biblioteca.gestion_libros.exceptions.RecursoNoEncontradoException;
import com.biblioteca.gestion_libros.model.LibroModel;
import com.biblioteca.gestion_libros.repository.LibroRepository;
import com.biblioteca.gestion_libros.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroModel guardarLibroRepository(LibroModel libro) {
        return libroRepository.save(libro);
    }
    
    @Override
    public List<LibroModel> obtenerLibrosRepository() {
        return libroRepository.findAll();
    }
    
    @Override
    public Optional<LibroModel> obtenerLibroRepositoryPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    @Override
    public LibroModel actualizarLibroRepository(Long id, LibroModel libroDetalles) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroDetalles.getTitulo());
            libro.setAutor(libroDetalles.getAutor());
            libro.setAnoPublicacion(libroDetalles.getAnoPublicacion());
            return libroRepository.save(libro);
        }).orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado con id " + id));
    }
    
    @Override
    public void eliminarLibroRepository(Long id) {
        libroRepository.deleteById(id);
    }
}