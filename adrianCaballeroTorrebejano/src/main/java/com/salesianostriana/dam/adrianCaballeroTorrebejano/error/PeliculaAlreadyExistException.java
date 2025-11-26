package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class PeliculaAlreadyExistException extends RuntimeException {
    public PeliculaAlreadyExistException(String message) {
        super(message);
    }

    public PeliculaAlreadyExistException(Long id){
        super("Pelicula con id: %d ya existe".formatted(id));
    }


}
