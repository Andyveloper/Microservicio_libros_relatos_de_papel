package com.unir.buscador_libros.data;

import com.unir.buscador_libros.data.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroJpaRepository extends JpaRepository<Libro, Long> {
  @Query("SELECT l FROM Libro l WHERE " +
          "(:id IS NULL OR l.id = :id) AND " +
          "(:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
          "(:autor IS NULL OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :autor, '%'))) AND " +
          "(:categoria IS NULL OR LOWER(l.categoria) LIKE LOWER(CONCAT('%', :categoria, '%'))) AND " +
          "(:isbn IS NULL OR STR(l.isbn) = :isbn) AND " +
          "(:valoracion IS NULL OR STR(l.valoracion) = :valoracion) AND " +
          "(:visibilidad IS NULL OR l.visibilidad = :visibilidad)")
  List<Libro> findAllByParams(
          @Param("id") Long id,
          @Param("titulo") String titulo,
          @Param("autor") String autor,
          @Param("categoria") String categoria,
          @Param("isbn") String isbn,
          @Param("valoracion") String valoracion,
          @Param("visibilidad") Boolean visibilidad
  );
}