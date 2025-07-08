package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Mechanical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicalRepository extends JpaRepository<Mechanical, Long> {

}