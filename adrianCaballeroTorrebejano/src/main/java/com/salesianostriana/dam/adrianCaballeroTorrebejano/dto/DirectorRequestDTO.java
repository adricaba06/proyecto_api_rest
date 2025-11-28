package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Set;
@Tag(name = "DirectorRequestDTO", description = "En vez de pasarle una entidad Director a los metodos del servicio, le pasamos un requestDTO")
public record DirectorRequestDTO(
        @Schema(description = "Nombre del director de una película")
        String nombre,
        @Schema(description = "Año de nacimeinto del director")
        int anioNacimiento

) {
}
