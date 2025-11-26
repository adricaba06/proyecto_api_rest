package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Long id){super("Entity with id: %d not found".formatted(id));}

    public EntityNotFoundException(){
        super("Entity not found");
    }
}
