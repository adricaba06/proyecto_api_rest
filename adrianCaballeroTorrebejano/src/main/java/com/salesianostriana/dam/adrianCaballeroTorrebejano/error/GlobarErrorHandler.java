package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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

    @ExceptionHandler(ActorAlreadyInCastException.class)
    public ProblemDetail handleActorAlreadyInCastException(ActorAlreadyInCastException ex){
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT,
                        ex.getMessage());
        result.setTitle("Actor ya en reparto");
        return result;
    }

    @ExceptionHandler(DirectorIsAMinorException.class)
    public ProblemDetail handleDirectorIsAMinor(DirectorIsAMinorException ex){
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT,
                        ex.getMessage());
        result.setTitle("El director es menor");
        return result;
    }






}
