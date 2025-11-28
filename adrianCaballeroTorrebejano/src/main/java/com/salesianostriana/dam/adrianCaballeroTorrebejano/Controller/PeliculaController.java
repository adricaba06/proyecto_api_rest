package com.salesianostriana.dam.adrianCaballeroTorrebejano.Controller;


import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.PeliculaResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("peliculas")
@RequiredArgsConstructor
@Tag(name = "Pelicula", description = "El controlador de peliculas, para poder realizar todas las operaciones de gestión")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtenemos todas las películas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de películas encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PeliculaResponseDTO.class)),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": 1,
                                            "titulo": "Interestelar",
                                            "fechaEstreno": "2014-11-07",
                                            "director": {
                                                "id": 1,
                                                "nombre": "Christopher Nolan"
                                            },
                                            "actores": []
                                        }
                                    ]
                                    """)
                    )
            )
    })
    public Set<PeliculaResponseDTO> getAll() {
        return peliculaService.listAllPeliculas().stream()
                .map(pelicula -> peliculaService.mapToResponseDTO(pelicula))
                .collect(Collectors.toSet());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Devuelve una película por su ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pelicula encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Película no  encontrada",
                                      "status": 404,
                                      "detail": "No existe la película con id 1",
                                      "instance": "/pelicula/1"
                                    }
                                    """)
                    )
            )
    })
    public PeliculaResponseDTO getPeliculaById(@PathVariable Long id) {
        return peliculaService.mapToResponseDTO(
                peliculaService.findPeliculaById(id)
        );
    }


    @PostMapping
    @Operation(summary = "Crea una nueva película")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos necesarios para crear una película",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = PeliculaRequestDTO.class),
                    examples = @ExampleObject(value = """
                            {
                                "titulo": "Origen",
                                "fechaEstreno": "2010-07-16",
                                "directorId": 1,
                                "actoresIds": [2, 3]
                            }
                            """)
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Película creada correctamente",
                    content = @Content(schema = @Schema(implementation = PeliculaResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error director menor de edad",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                    {
                                        "title": "Director es menor de edad",
                                        "status": 400,
                                         ,
                                        "instance": "/pelicula"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDTO> createPelicula(
            @RequestBody PeliculaRequestDTO req) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peliculaService.mapToResponseDTO(
                        peliculaService.savePelicula(req)
                ));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Edita una pelicula existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Película actualizada correctamente",
                    content = @Content(schema = @Schema(implementation = PeliculaResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada "
            )
    })
    public PeliculaResponseDTO updatePelicula(
            @RequestBody PeliculaRequestDTO req,
            @PathVariable Long id) {

        return peliculaService.mapToResponseDTO(
                peliculaService.updatePelicula(id, req)
        );
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una película")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Película eliminada"),
            @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        peliculaService.deletePeliculaById(id);
        return ResponseEntity.noContent().build();
    }


    //asignar

    @PostMapping("/{peliculaId}/actores/{actorId}")
    @Operation(
            summary = "Añadir un actor a una película",
            description = "Asocia un actor existente a una película mediante la relación Muchos a Muchos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor añadido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO.class),
                            examples = @ExampleObject("""
                                {
                                    "id": 1,
                                    "titulo": "Interstellar",
                                    "genero": "Ciencia Ficción",
                                    "fechaEstreno": "2014-11-07",
                                    "director": {
                                        "id": 1,
                                        "nombre": "Christopher Nolan"
                                    },
                                    "actores": [
                                        {"id": 1, "nombre": "Matthew McConaughey"},
                                        {"id": 2, "nombre": "Anne Hathaway"},
                                        {"id": 4, "nombre": "Nuevo Actor Añadido"}
                                    ]
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "title": "Película o actor no encontrado",
                                  "status": 404,
                                  "detail": "No existe la película con id 5 o el actor con id 22",
                                  "instance": "/pelicula/5/actores/22"
                                }
                                """)
                    )
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Actor no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "title": "Actor no encontrado",
                                  "status": 400,
                                  "detail": "No existe Sel actor con id 22",
                                  "instance": "/pelicula/5/actores/22"
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = " actor ya en reparto",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject("""
                                {
                                  "title": "El actor ya pertenece a esta película",
                                  "status": 400,
                                  "detail": "El actor con id 1 ya está asociado a la película con id 1",
                                  "instance": "/pelicula/1/actores/1"
                                }
                                """)
                    )
            )
    })
    public PeliculaResponseDTO addActorToPelicula(
            @PathVariable Long peliculaId,
            @PathVariable Long actorId) {

        Pelicula pelicula = peliculaService.addActor(peliculaId, actorId);
        return peliculaService.mapToResponseDTO(pelicula);
    }
}