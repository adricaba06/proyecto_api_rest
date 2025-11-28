package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class ActorBadRequestException extends RuntimeException {

    public ActorBadRequestException(String message) {
        super(message);
    }

    public ActorBadRequestException(Long id){
      super("Actor con id %d no existe".formatted(id));
    }
}
