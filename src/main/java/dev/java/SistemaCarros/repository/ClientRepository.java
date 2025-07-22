package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
