package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.ActorSimpleDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.DirectorSimpleDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.Set;
@Tag(name = "PeliculaResponseDTO", description = "En vez de una Pelicula, devolvemos un responseDTO")
public record PeliculaResponseDTO(

        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        Set<ActorSimpleDTO> actores,
        DirectorSimpleDTO director
        ) {
}
