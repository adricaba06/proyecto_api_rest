package com.salesianostriana.dam.adrianCaballeroTorrebejano.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;


    @ManyToMany
    @JoinTable(name = "actores_peliculas",
            joinColumns = @JoinColumn(name="pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Actor> actores;

}

//https://stackoverflow.com/questions/3987799/jpa-with-hibernate-3-manytomany-stack-overflow-and-multiple-bag-errors
