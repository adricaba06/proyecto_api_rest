package com.salesianostriana.dam.adrianCaballeroTorrebejano.dto;

import org.apache.tomcat.util.codec.binary.StringUtils;

public record DirectorResponseDTO(
            Long id,
            String nombre,
            int anioNacimiento

) {
}
