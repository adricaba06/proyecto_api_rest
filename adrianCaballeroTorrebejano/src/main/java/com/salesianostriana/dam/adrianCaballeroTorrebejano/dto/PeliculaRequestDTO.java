package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import java.time.LocalDate;

public record PeliculaRequestDTO(
         String titulo,
         String genero,
         LocalDate fechaEstreno
) {
}
