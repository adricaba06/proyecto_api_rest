package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.codec.binary.StringUtils;
@Tag(name = "ActorRequestDTO", description = "En vez de pasarle un Actor a los metodos del servicio, le pasamos un requestDTO")
public record DirectorResponseDTO(

            @Schema(description = "Identificador unico de un director de pelicula")
            Long id,
            @Schema(description = "Nombre del director de una pelicula")
            String nombre,
            @Schema(description = "Año de nacimiento del director de una película, solo el año y es un integer")
            int anioNacimiento

) {
}
