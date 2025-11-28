package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class DirectorBadRequestException extends RuntimeException {

    public DirectorBadRequestException(String message) {
        super(message);
    }

  public DirectorBadRequestException(Long id) {
    super("El director con id %d no existe".formatted(id));
  }
}
