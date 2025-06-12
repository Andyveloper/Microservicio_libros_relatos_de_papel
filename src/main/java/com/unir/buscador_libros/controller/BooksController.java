package com.unir.buscador_libros.controller;

import com.unir.buscador_libros.data.model.Libro;
import com.unir.buscador_libros.service.LibrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BooksController {


  private final LibrosService librosService;

  @GetMapping("/libros")
  public ResponseEntity<List<Libro>> getBooks() {
    List<Libro> libros = librosService.getBooks();
    return libros != null ? ResponseEntity.ok(libros) : ResponseEntity.ok(Collections.emptyList());
  }

  @GetMapping("/libros/buscar")
  public ResponseEntity<List<Libro>> getBooksByParams(
          @RequestParam(required = false) Long id,
          @RequestParam(required = false) String titulo,
          @RequestParam(required = false) String autor,
          @RequestParam(required = false) String categoria,
          @RequestParam(required = false) String isbn,
          @RequestParam(required = false) String valoracion,
          @RequestParam(required = false) Boolean visibilidad
  ) {
    List<Libro> resultados = librosService.getBooksByParams(id, titulo, autor, categoria, isbn, valoracion, visibilidad);
    return resultados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resultados);
  }

  @GetMapping("/libros/{id}")
  public ResponseEntity<Libro> getBookById(@PathVariable Long id) {
    Libro libro = librosService.getLibroById(id);
    return libro != null ? ResponseEntity.ok(libro) : ResponseEntity.notFound().build();
  }

  @PostMapping("/libros")
  public ResponseEntity<Libro> createBook(@RequestBody Libro libro) {
    Libro libroRs = librosService.createBook(libro);
    if (libroRs != null) {
      return ResponseEntity.ok(libroRs);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/libros/{id}")
  public ResponseEntity<Libro> updateBook(@PathVariable Long id, @RequestBody Libro libro) {
    Libro libroParaActualizar = librosService.getLibroById(id);
    if (libroParaActualizar == null) {
      return ResponseEntity.notFound().build();
    }
    BeanUtils.copyProperties(libro, libroParaActualizar, "id");
    Libro libroActualizado = librosService.updateBook(libroParaActualizar);
    return ResponseEntity.ok(libroActualizado);
  }

  @PatchMapping("/libros/{id}")
  public ResponseEntity<Libro> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> camposActualizados) {
    Libro libroParaActualizar = librosService.getLibroById(id);
    if (libroParaActualizar == null) {
      return ResponseEntity.notFound().build();
    }
    camposActualizados.forEach((clave, valor) -> {
      switch (clave) {
        case "titulo" -> libroParaActualizar.setTitulo((String) valor);
        case "autor" -> libroParaActualizar.setAutor((String) valor);
        case "fechaPublicacion" -> libroParaActualizar.setFechaPublicacion((LocalDate) valor);
        case "categoria" -> libroParaActualizar.setCategoria((String) valor);
        case "isbn" -> libroParaActualizar.setIsbn((int) valor);
        case "valoracion" -> libroParaActualizar.setValoracion((int) valor);
        case "visibilidad" -> libroParaActualizar.setVisibilidad((boolean) valor);
        case "cantidad" -> libroParaActualizar.setCantidad((int) valor);
        case "precio" -> libroParaActualizar.setPrecio((double) valor);
      }
    });
    Libro libroActualizado = librosService.updateBook(libroParaActualizar);
    return ResponseEntity.ok(libroActualizado);
  }

  @DeleteMapping("/libros/{id}")
  public ResponseEntity<Libro> deleteBook(@PathVariable Long id) {
    Libro libroRs = librosService.deleteBook(id);
    if (libroRs != null) {
      return ResponseEntity.ok(libroRs);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}