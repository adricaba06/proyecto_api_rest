package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class ActorAlreadyInCastException extends RuntimeException {
    public ActorAlreadyInCastException(String message) {
        super(message);
    }

    public ActorAlreadyInCastException(Long id){
        super("Actor con id: %d ya está en esta película".formatted(id));
    }

    public ActorAlreadyInCastException(){
        super("Actor ya esta en reparto");
    }

}
