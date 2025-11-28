package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ActorRequestDTO", description = "En vez de pasarle un Actor a los metodos del servicio, le pasamos un requestDTO")
public record ActorRequestDTO(
        @Schema(description = "Nombre del actor", example = "Tom Cruise")
        String nombre

) {}
