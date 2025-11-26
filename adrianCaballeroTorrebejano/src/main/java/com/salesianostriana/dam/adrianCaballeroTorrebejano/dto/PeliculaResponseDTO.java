package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.ActorSimpleDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.DirectorSimpleDTO;

import java.time.LocalDate;
import java.util.Set;

public record PeliculaResponseDTO(

        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        Set<ActorSimpleDTO> actores,
        DirectorSimpleDTO director
        ) {
}
