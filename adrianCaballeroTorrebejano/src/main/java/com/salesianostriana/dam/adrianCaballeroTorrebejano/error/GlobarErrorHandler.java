package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice //mapear excepciones a respuestas htpp, basicamnete traducir
public class GlobarErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex){
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,
                ex.getMessage());
        result.setTitle("Entidad no encontrada");
        result.setType(URI.create(""));
        return result;
    }

    @ExceptionHandler(PeliculaAlreadyExistException.class)
    public ProblemDetail handlePeliculaAlreadyExistException(PeliculaAlreadyExistException ex){
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT,
                ex.getMessage());

        result.setTitle("Pelicula ya existe");
        return result;
    }

    @ExceptionHandler(ActorYaEnRepartoException.class)
    public ProblemDetail handleActorYaEnReparto(ActorYaEnRepartoException ex){
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT,
                        ex.getMessage());
        result.setTitle("Actor ya en reparto");
        return result;
    }

    @ExceptionHandler(DirectorIsAMinorException.class)
    public ProblemDetail handleDirectorIsAMinor()






}
