package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByUsuarioId(Long usuarioId);
}
