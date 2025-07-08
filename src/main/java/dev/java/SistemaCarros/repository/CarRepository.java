package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByClientId(Long usuarioId);
}
