package com.unir.buscador_libros.service;

import com.unir.buscador_libros.data.model.Libro;

import java.util.List;


public interface LibrosService {

  List<Libro> getBooks();

  List<Libro> getBooksByParams(Long id, String titulo, String autor, String categoria, String isbn, String valoracion, Boolean visibilidad);

  Libro getLibroById(Long id);

  Libro createBook(Libro Libro);

  Libro updateBook(Libro Libro);

  Libro deleteBook(Long id);
}