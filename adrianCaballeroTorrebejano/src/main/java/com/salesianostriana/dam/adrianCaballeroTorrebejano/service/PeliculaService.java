package com.salesianostriana.dam.adrianCaballeroTorrebejano.service;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.ActorSimpleDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.simples.DirectorSimpleDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.*;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Actor;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.ActorRepoitory;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.DirectorRepository;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.PeliculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    private final DirectorRepository directorRepository;
    private final ActorRepoitory actorRepository;

    //traducción y metodos de validacion

    public Pelicula mapToEntity(PeliculaRequestDTO req) {

        Director director = directorRepository.findById(req.directorId())
                .orElseThrow(() -> new DirectorBadRequestException(req.directorId()));

        /*Set<Actor> actores = new HashSet<>();
        actorRepository.findAllById(req.actoresId()).forEach(actor -> actores.add(actor));*/

        Set<Actor> actores = req.actoresId()
                .stream()
                .map(id -> actorRepository.findById(id)
                        .orElseThrow(() -> new ActorBadRequestException(id))).collect(Collectors.toSet());

        return Pelicula.builder()
                .titulo(req.titulo())
                .genero(req.genero())
                .fechaEstreno(req.fechaEstreno())
                .actores(actores)
                .director(director)
                .build();
    }


    public PeliculaResponseDTO mapToResponseDTO(Pelicula pelicula) {
        DirectorSimpleDTO directorSimpleDTO = new DirectorSimpleDTO(
                pelicula.getDirector().getId(), pelicula.getDirector().getNombre()
        );

        Set<ActorSimpleDTO> actores = pelicula.getActores()
                .stream()
                .map(actor -> new ActorSimpleDTO(actor.getId(), actor.getNombre()))
                .collect(Collectors.toSet());

        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                actores,
                directorSimpleDTO
        );

    }

    public void validateAge(PeliculaRequestDTO req) {
        int estreno;
        int edadEnEstreno;
        Director director = directorRepository.findById(req.directorId())
                .orElseThrow(() -> new DirectorBadRequestException(req.directorId()));

         estreno = req.fechaEstreno().getYear();

         edadEnEstreno = estreno - director.getAnioNacimiento();

        if (edadEnEstreno < 18) {
            throw new DirectorIsAMinorException(req.directorId());
        }
    }


    public void validatePeliculaRequest(PeliculaRequestDTO req) {

        if (!StringUtils.hasText(req.titulo())
                || !StringUtils.hasText(req.genero())
                || req.fechaEstreno() == null
                || req.directorId() == null
                || req.actoresId() == null
                || req.actoresId().isEmpty()) {

            throw new IllegalArgumentException("No se pudo crear la películam, los datos estan incompletos");
        }

    } // aqui es donde me aseguro de que el director entre ootras cosas no sea nulo
    // Pelicula debe requerir el id de un Director existente.

    public Set<ActorSimpleDTO> mapActores(Set<Actor> actores) {
        return actores.stream()
                .map(a -> new ActorSimpleDTO(a.getId(), a.getNombre()))
                .collect(Collectors.toSet());
    }


    //crud

    public Set<Pelicula> listAllPeliculas() {
        List<Pelicula> lista = peliculaRepository.findAll();
        Set<Pelicula> setOfPeliculas = new HashSet<>(lista);

        if (setOfPeliculas.isEmpty()) {
            throw new PeliculaNotFoundException("No hay ninguna entidad");
        }

        return setOfPeliculas;
    }

    public Pelicula findPeliculaById(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));

        return pelicula;
    }

    public Pelicula savePelicula(PeliculaRequestDTO req) {
        validatePeliculaRequest(req);
        validateAge(req);
        peliculaRepository.findByTituloIgnoreCase(req.titulo())
                .ifPresent(p -> {
                    throw new PeliculaAlreadyExistException(p.getId());

                });

        Pelicula pelicula = mapToEntity(req);

        return peliculaRepository.save(pelicula);
    }

    public Pelicula updatePelicula(Long id, PeliculaRequestDTO req) {

        Director director = directorRepository.findById(req.directorId())
                .orElseThrow(() -> new DirectorBadRequestException(req.directorId()));

        Set<Actor> actores = actorRepository.findAllById(req.actoresId())
                .stream().collect(Collectors.toSet());

        validatePeliculaRequest(req);
        validateAge(req);

        if (actores.size() != req.actoresId().size()) {
            throw new ActorBadRequestException("Alguno de los actores no existe");
        }

        return peliculaRepository.findById(id).map(p -> {
                    p.setTitulo(req.titulo());
                    p.setGenero(req.genero());
                    p.setFechaEstreno(req.fechaEstreno());
                    p.setActores(actores);
                    p.setDirector(director);

                    return peliculaRepository.save(p);

                })
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }


    public void deletePeliculaById(Long id) {
        Pelicula pelicula = peliculaRepository
                .findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));

        peliculaRepository.delete(pelicula);
    }

    //asignacion

    public Pelicula addActor(Long idPelicula, Long idActor) {

        Pelicula pelicula = peliculaRepository
                .findById(idPelicula)
                .orElseThrow(() -> new PeliculaNotFoundException(idPelicula));

        Actor actor = actorRepository.findById(idActor)
                .orElseThrow(() -> new ActorBadRequestException(idActor));

        if (pelicula.getActores().contains(actor)) { //los set no trabajan con indice, por ello he utilizado .conatains
            throw new ActorAlreadyInCastException(idActor);

        }

        pelicula.getActores().add(actor);

        return peliculaRepository.save(pelicula);
    }


}
