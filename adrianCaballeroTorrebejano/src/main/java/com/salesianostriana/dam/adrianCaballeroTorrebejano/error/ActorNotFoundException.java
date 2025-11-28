package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class ActorNotFoundException extends EntityNotFoundException {
    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(Long id){
        super("Actor con id: %d no encontrado".formatted(id));
    }



}
