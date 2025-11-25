package com.salesianostriana.dam.adrianCaballeroTorrebejano.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private int anioNacieminto;

}
