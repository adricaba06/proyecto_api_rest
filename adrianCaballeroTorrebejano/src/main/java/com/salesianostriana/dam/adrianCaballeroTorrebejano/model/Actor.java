package com.salesianostriana.dam.adrianCaballeroTorrebejano.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany
    private List<Pelicula> peliculas;


}
