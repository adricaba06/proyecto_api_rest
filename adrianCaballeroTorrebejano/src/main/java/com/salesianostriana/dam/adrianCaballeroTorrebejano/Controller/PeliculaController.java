package com.salesianostriana.dam.adrianCaballeroTorrebejano.Controller;


import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.service.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public Set<Pelicula> getAll() {
        return peliculaService.listAllPeliculas();
    }

    @GetMapping("/{id}")
    public PeliculaResponseDTO getPeliculaById(@PathVariable Long id) {
        Pelicula peliculaEntity = peliculaService.findPeliculaById(id);
        PeliculaResponseDTO peliculaResponseDTO = peliculaService.mapToResponseDTO(peliculaEntity);
        return peliculaResponseDTO;
    }

    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> createPelicula(
            @RequestBody PeliculaRequestDTO req
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peliculaService.mapToResponseDTO(peliculaService.savePelicula(req)));

    }

    @PostMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> updatePelicula(
            @RequestBody PeliculaRequestDTO req,
            @PathVariable Long id) {

        Pelicula updated = peliculaService.updatePelicula(id, req);
        PeliculaResponseDTO dto = peliculaService.mapToResponseDTO(updated);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        peliculaService.deletePeliculaById(id);
        return ResponseEntity.noContent().build();
    }


}
