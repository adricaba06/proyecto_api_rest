package com.salesianostriana.dam.adrianCaballeroTorrebejano.service;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.ActorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.ActorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.ActorNotFoundException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Actor;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.ActorRepoitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ActorService {

    private final ActorRepoitory actorRepoitory;

    //traduccion

    public Actor mapToEntity(ActorRequestDTO req) {
        return Actor.builder()
                .nombre(req.nombre())
                .build();
    }

    public ActorResponseDTO maptoDto(Actor actor) {
        return new ActorResponseDTO(
                actor.getId(),
                actor.getNombre()
        );
    }

    //crud
    public Set<Actor> listAllActores(){
        List<Actor> lista = actorRepoitory.findAll();
        Set<Actor> setOfActores= new HashSet<>(lista);

        if(setOfActores.isEmpty()){
            throw new ActorNotFoundException("No hay actores");
        }

        return setOfActores;
    }

    public Actor saveActor(ActorRequestDTO req){

        if (!StringUtils.hasText(req.nombre())) {
            throw new IllegalArgumentException("El nombre del actor es obligatorio");
        }

        Actor actor = mapToEntity(req);
        return actorRepoitory.save(actor);
    }




}
