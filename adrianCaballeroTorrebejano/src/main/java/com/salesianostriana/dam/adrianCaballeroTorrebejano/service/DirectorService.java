package com.salesianostriana.dam.adrianCaballeroTorrebejano.service;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.AnioInvalidoException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.error.DirectorNotFoundException;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    //traducción y validacion

    public Director mapToEntity(DirectorRequestDTO req) {
        return Director.builder()
                .nombre(req.nombre())
                .anioNacimiento(req.anioNacimiento())
                .build();
    }

    public DirectorResponseDTO maptoDto(Director director) {
        return new DirectorResponseDTO(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento()
        );
    }


    public void validateYear(DirectorRequestDTO req) {
        int currentYear;
        currentYear = LocalDate.now().getYear();

        if (req.anioNacimiento() <= 0 || req.anioNacimiento() > currentYear) {
            throw new AnioInvalidoException("El año no es válido");

        }
    }

    //crud

    public Set<Director> listAllDirectores() {
        List<Director> lista = directorRepository.findAll();
        Set<Director> setOfDirectores = new HashSet<>(lista);

        if (setOfDirectores.isEmpty()) {
            throw new DirectorNotFoundException("No hay ningun director");
        }
        return setOfDirectores;
    }

    public Director findDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
        return director;
    }

    public Director saveDirector(DirectorRequestDTO req) {
        validateYear(req);

        if (!StringUtils.hasText(req.nombre())) {
            throw new IllegalArgumentException("Datos invalidos");
        }

        return directorRepository.save(mapToEntity(req));

    }

    public Director updateDirector(Long id, DirectorRequestDTO req) {
        validateYear(req);

        return directorRepository.findById(id).map(d -> {
                    d.setNombre(req.nombre());
                    d.setAnioNacimiento(req.anioNacimiento());
                    return directorRepository.save(d);
                }

        ).orElseThrow(() -> new DirectorNotFoundException(id));

    }

    public void deleteDirectorById(Long id){
        Director director = directorRepository
                .findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
        directorRepository.delete(director);
    }



}
