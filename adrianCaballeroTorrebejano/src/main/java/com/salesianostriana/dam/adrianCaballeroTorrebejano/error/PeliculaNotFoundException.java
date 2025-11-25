package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class PeliculaNotFoundException extends EntityNotFoundException {
    public PeliculaNotFoundException(String message) {
        super(message);
    }

    public PeliculaNotFoundException(Long id){
        super("Pelicula con id: %id no encontrada".formatted(id));
    }

}

