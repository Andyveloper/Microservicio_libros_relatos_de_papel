package com.unir.buscador_libros.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequest {

    @NotNull(message = "El titulo no puede ser null")
    private String titulo;

    @NotNull(message = "El autor no puede ser null")
    private String autor;

    @NotNull(message = "La fecha de publicacion no puede ser null")
    private LocalDate fechaPublicacion;

    @NotNull(message = "La categoria de publicacion no puede ser null")
    private String categoria;

    @NotNull(message = "El isbn de publicacion no puede ser null")
    private int isbn;

    @NotNull(message = "La valoracion de publicacion no puede ser null")
    private int valoracion;

    @NotNull(message = "La visibilidad de publicacion no puede ser null")
    private boolean visibilidad;

    @NotNull(message = "La cantidad de publicacion no puede ser null")
    private int cantidad;

    @NotNull(message = "El precio de publicacion no puede ser null")
    private double precio;

}