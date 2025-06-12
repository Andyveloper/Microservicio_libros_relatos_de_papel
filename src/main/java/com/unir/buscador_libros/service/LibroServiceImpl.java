package com.unir.buscador_libros.service;

import com.unir.buscador_libros.data.LibroJpaRepository;
import com.unir.buscador_libros.data.model.Libro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibrosService {

  private final LibroJpaRepository libroJpaRepository;

  @Override
  public List<Libro> getBooks() {
    List<Libro> libros = libroJpaRepository.findAll();
    return libros.isEmpty() ? null : libros;
  }

  @Override
  public List<Libro> getBooksByParams(Long id, String titulo, String autor, String categoria, String isbn, String valoracion, boolean visibilidad) {
    return libroJpaRepository.findAllByParams(id, titulo, autor, categoria, isbn, valoracion, visibilidad);
  }

  @Override
  public Libro getLibroById(Long id) {
    return libroJpaRepository.findById(id).orElse(null);
  }

  @Override
  public Libro createBook(Libro libro) {
    Libro request = Libro.builder().
            titulo(libro.getTitulo()).
            autor(libro.getAutor()).
            fechaPublicacion(libro.getFechaPublicacion()).
            valoracion(libro.getValoracion()).
            visibilidad(libro.isVisibilidad()).
            isbn(libro.getIsbn()).
            cantidad(libro.getCantidad()).
            categoria(libro.getCategoria()).
            precio(libro.getPrecio())
            .build();
    return libroJpaRepository.save(request);
  }

  @Override
  public Libro updateBook(Libro libro) {
    return libroJpaRepository.save(libro);
  }

  @Override
  public Libro deleteBook(Long id) {
    Libro libro = libroJpaRepository.findById(id).orElse(null);
    if (libro != null) {
      libroJpaRepository.delete(libro);
      return libro;
    }
    return null;
  }
}