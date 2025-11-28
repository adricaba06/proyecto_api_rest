package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ActorResponseDTO", description = "En vez de un Actor, devolvemos un responseDTO")
public record ActorResponseDTO(

        @Schema(description = "Identificador unico de Actor")
        Long id,
        @Schema(description = "Nombre del actor")
        String nombre
) {
}
