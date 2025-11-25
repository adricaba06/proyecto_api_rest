package com.salesianostriana.dam.adrianCaballeroTorrebejano.repository;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface directorRepository extends JpaRepository<Director, Long> {
}
