package com.salesianostriana.dam.adrianCaballeroTorrebejano.error;

public class DirectorIsAMinorException extends RuntimeException {
    public DirectorIsAMinorException(String message) {
        super(message);
    }

    public DirectorIsAMinorException(){
      super("El director es menor");
    }

    public DirectorIsAMinorException(Long id){
    super("El director con este id: %d es menor".formatted(id));
  }


}
