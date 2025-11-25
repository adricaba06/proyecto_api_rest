package com.salesianostriana.dam.adrianCaballeroTorrebejano.service;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.EntityNotFoundException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.PeliculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public Set<Pelicula> listAllPeliculas(){
        List<Pelicula> lista = peliculaRepository.findAll();
        Set<Pelicula> setOfPeliculas = new HashSet<>(lista);

        if(setOfPeliculas.isEmpty()){
            throw new EntityNotFoundException("No hay ninguna entidad");
        }

        return setOfPeliculas;
    }

    public Pelicula addPelicula(PeliculaRequestDTO cmd){  // usuario --> (dto --> entidad)  --> bd
        if(!StringUtils.hasText(cmd.titulo())
        || !StringUtils.hasText(cmd.genero()) || !StringUtils.hasText(cmd.genero())
        ){
            throw  new IllegalArgumentException("No se puedo crear Pelicula");
        }
        return
    }


}
