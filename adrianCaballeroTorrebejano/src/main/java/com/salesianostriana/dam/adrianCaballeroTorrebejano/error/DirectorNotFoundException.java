package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class DirectorNotFoundException extends EntityNotFoundException {
    public DirectorNotFoundException(String message) {
        super(message);
    }

    public DirectorNotFoundException(Long id){
        super("Director con id: %d no encontrado".formatted(id));
    }

}
