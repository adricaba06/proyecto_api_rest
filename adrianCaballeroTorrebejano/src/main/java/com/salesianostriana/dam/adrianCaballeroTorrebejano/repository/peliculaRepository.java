package com.salesianostriana.dam.adrianCaballeroTorrebejano.repository;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface peliculaRepository extends JpaRepository<Pelicula, Long> {
}
