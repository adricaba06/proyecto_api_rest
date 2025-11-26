package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;

import java.time.LocalDate;

public record PeliculaRequestDTO( //createDTO
         String titulo,
         String genero,
         LocalDate fechaEstreno
) {

    public Pelicula toEntity(){
        return Pelicula.builder()
                .titulo(this.titulo)
                .genero(this.genero)
                .fechaEstreno(this.fechaEstreno)
                .build();

    }

    //Si necesita datos externos al dto mejot hacer la transformarcion en el servicio

}


