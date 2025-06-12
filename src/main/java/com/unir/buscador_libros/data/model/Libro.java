package com.unir.buscador_libros.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "libro_id")
  private Long id;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "autor")
  private String autor;

  @Column(name = "fecha_publicacion")
  private LocalDate fechaPublicacion;

  @Column(name = "categoria")
  private String categoria;

  @Column(name = "isbn", unique = true)
  private int isbn;

  @Column(name = "valoracion")
  private int valoracion;

  @Column(name = "visibilidad")
  private boolean visibilidad;

  @Column(name = "cantidad")
  private int cantidad;

  @Column(name = "precio")
  private double precio;
}