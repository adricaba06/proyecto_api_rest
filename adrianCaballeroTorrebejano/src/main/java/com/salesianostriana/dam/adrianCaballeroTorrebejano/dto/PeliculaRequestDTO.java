package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;

import java.time.LocalDate;

public record PeliculaRequestDTO(
         String titulo,
         String genero,
         LocalDate fechaEstreno
) {

    public Pelicula toEntity(PeliculaRequestDTO cmd){
        return Pelicula.builder()
                .titulo(cmd.titulo)
                .genero(cmd.genero)
                .fechaEstreno(cmd.fechaEstreno)
                .build();

        );

    }

}


