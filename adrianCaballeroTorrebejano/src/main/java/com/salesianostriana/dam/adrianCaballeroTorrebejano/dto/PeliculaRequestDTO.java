package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import java.time.LocalDate;
import java.util.Set;

public record PeliculaRequestDTO( //createDTO
                                  String titulo,
                                  String genero,
                                  LocalDate fechaEstreno,
                                  Set<Long> actoresId,
                                  Long directorId
) {

    //Si necesita datos externos al dto mejot hacer la transformarcion en el servicio


}


