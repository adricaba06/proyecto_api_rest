package com.salesianostriana.dam.adrianCaballeroTorrebejano.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    private int anioNacimiento;

    @OneToMany(mappedBy = "director",  cascade = CascadeType.REMOVE)
    private Set<Pelicula> peliculas;

}
