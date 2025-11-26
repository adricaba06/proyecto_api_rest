package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class ActorYaEnRepartoException extends RuntimeException {
    public ActorYaEnRepartoException(String message) {
        super(message);
    }

    public ActorYaEnRepartoException(Long id){
        super("Actor con id: %d ya está en esta película");
    }

    public ActorYaEnRepartoException(){
        super("Actor ya esta en reparto");
    }

}
