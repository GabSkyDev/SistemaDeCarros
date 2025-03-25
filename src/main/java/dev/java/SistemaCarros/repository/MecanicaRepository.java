package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Mecanica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MecanicaRepository extends JpaRepository<Mecanica, Long> {

}