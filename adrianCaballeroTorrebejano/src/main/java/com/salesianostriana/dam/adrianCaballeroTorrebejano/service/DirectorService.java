package com.salesianostriana.dam.adrianCaballeroTorrebejano.service;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.ActorNotFoundException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.DirectorNotFoundException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Actor;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    //traducción

    public Director mapToEntity(DirectorRequestDTO req) {

        return Director.builder()
                .nombre(req.nombre())
                .anioNacieminto(req.anioNacimiento())
                .build();
    }

    public DirectorResponseDTO maptoDto(Director director){
        return new DirectorResponseDTO(
                director.getId(),
                director.getNombre(),
                director.getAnioNacieminto()
        );
    }

    public Director saveDirector(DirectorRequestDTO req){
        if(!StringUtils.hasText(req.nombre()))
    }





}
