package com.salesianostriana.dam.adrianCaballeroTorrebejano.repository;

import com.salesianostriana.dam.adrianCaballeroTorrebejano.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface actorRepoitory extends JpaRepository<Actor, Long> {



}
