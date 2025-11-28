package com.salesianostriana.dam.adrianCaballeroTorrebejano.Controller;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.ActorRequestDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.dto.ActorResponseDTO;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Actor;
import com.salesianostriana.dam.adrianCaballeroTorrebejano.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("actores")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    @Operation(
            summary = "Devuelve todos los actores",
            description = "Devuelve todos los actores "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de actores obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ActorResponseDTO.class)),
                            examples = @ExampleObject("""
                                [
                                  {
                                    "id": 1,
                                    "nombre": "Leonardo DiCaprio",
                                    "edad": 49
                                  },
                                  {
                                    "id": 2,
                                    "nombre": "Natalie Portman",
                                    "edad": 42
                                  }
                                ]
                                """)
                    )
            )
    })
    public Set<ActorResponseDTO> getAllActores() {
        return actorService.listAllActores().stream()
                .map(actor -> actorService.maptoDto(actor)).collect(Collectors.toSet());
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo actor",
            description = "Registra un nuevo actor y devuelve la entidad creada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Actor creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Actor.class),
                            examples = @ExampleObject("""
                                {
                                  "id": 5,
                                  "nombre": "Keanu Reeves",
                                  "edad": 59
                                }
                                """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos enviados en la petición",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public Actor createActor(@RequestBody ActorRequestDTO req) {
        return actorService.saveActor(req);
    }


}
