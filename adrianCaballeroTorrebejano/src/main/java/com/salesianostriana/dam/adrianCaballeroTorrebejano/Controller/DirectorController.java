package com.salesianostriana.dam.adrianCaballeroTorrebejano.Controller;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.DirectorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @Operation(
            summary = "Obtener todos los directores",
            description = "Devuelve una lista con todos los directores registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DirectorResponseDTO.class)),
                            examples = @ExampleObject("""
                                [
                                  {
                                    "id": 1,
                                    "nombre": "Christopher Nolan",
                                    "anioNacimiento": 1970
                                  },
                                  {
                                    "id": 2,
                                    "nombre": "Steven Spielberg",
                                    "anioNacimiento": 1946
                                  }
                                ]
                                """)
                    )
            )
    })
    public Set<DirectorResponseDTO> getAll() {
        return directorService.listAllDirectores()
                .stream()
                .map(director -> directorService.maptoDto(director))
                .collect(Collectors.toSet());
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un director por su ID",
            description = "Devuelve la información del director solicitado si existe."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
                            examples = @ExampleObject("""
                                {
                                  "id": 1,
                                  "nombre": "Christopher Nolan",
                                  "anioNacimiento": 1970
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "director no encontrasdo ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "title": "Director no encontrado",
                                  "status": 404,
                                  "instance": "/director/99"
                                }
                                """)
                    )
            )
    })
    public DirectorResponseDTO getDirectorById(@PathVariable Long id){
        Director directorE = directorService.findDirectorById(id);
        return directorService.maptoDto(directorE);
    }

    @PostMapping
    @Operation(
            summary = "Crea un nuevo director",
            description = "crea un director con los datos proporcionados."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Director creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
                            examples = @ExampleObject("""
                                {
                                  "id": 5,
                                  "nombre": "James Cameron",
                                  "anioNacimiento": 1954
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "title": "Datos no válidos",
                                  "status": 400,
                                  "detail": "El año de nacimiento no puede ser futuro",
                                  "instance": "/director"
                                }
                                """)
                    )
            )
    })
    public ResponseEntity<DirectorResponseDTO> createDirector(
            @RequestBody DirectorRequestDTO req){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(directorService.maptoDto(directorService.saveDirector(req)));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza a  un director",
            description = "Modifica los datos del director indicado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
                            examples = @ExampleObject("""
                                {
                                  "id": 3,
                                  "nombre": "Ridley Scott",
                                  "anioNacimiento": 1937
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El director no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public DirectorResponseDTO updateDirector(
            @RequestBody DirectorRequestDTO req,
            @PathVariable Long id) {

        Director updated = directorService.updateDirector(id, req);
        return directorService.maptoDto(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un director",
            description = "Elimina el director si no está asociado a ninguna película."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Director eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "No se puede eliminar porque tiene películas asociadas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "type": "http://dam.salesianos-triana.com/director-has-movies",
                                  "title": "No se puede eliminar el director",
                                  "status": 409,
                                  "detail": "El director con id 1 tiene películas asociadas",
                                  "instance": "/director/1"
                                }
                                """)
                    )
            )
    })
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirectorById(id);
        return ResponseEntity.noContent().build();
    }


}
