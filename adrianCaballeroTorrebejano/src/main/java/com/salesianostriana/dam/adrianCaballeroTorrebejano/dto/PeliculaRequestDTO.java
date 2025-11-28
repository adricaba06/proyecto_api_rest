package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.Set;
@Tag(name = "PeliculaRequestDTO", description = "En vez de pasar una entidad Pelicula a los metodos del servicio, le pasamos un requestDTO")
public record PeliculaRequestDTO(
                                  @Schema(description = "Titulo de una pelicula, no puden haber dos peliculas con el mismo titulo")
                                  String titulo,
                                  @Schema(description = "Genero de una pelicula", example = "Suspense")
                                  String genero,
                                  @Schema(description = "Fecha de extreno de una película, puede haber sido ya extrenada o estar por extrenar")
                                  LocalDate fechaEstreno,
                                  @Schema(description = "Listado de Ids de actores para no pasar la entidad entera")
                                  Set<Long> actoresId,
                                  @Schema(description = "Listado de Ids de directores para no pasar la entidad entera")
                                  Long directorId
) {

    //Si necesita datos externos al dto mejot hacer la transformarcion en el servicio

}


