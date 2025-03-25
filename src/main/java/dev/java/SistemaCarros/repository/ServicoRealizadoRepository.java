package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.ServicoRealizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRealizadoRepository extends JpaRepository<ServicoRealizado, Long> {
}
