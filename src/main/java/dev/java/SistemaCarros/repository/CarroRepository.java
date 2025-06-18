package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByPlaca(String placa);
    List<Carro> findByModelo(String modelo);
    List<Carro> findByMarca(String marca);
    List<Carro> findByAnoFabricacao(Integer anoFabricacao);
    List<Carro> findByCor(String cor);
    List<Carro> findByTipoCombustivel(String tipoCombustivel);
    List<Carro> findByTransmissao(String transmissao);
    List<Carro> findByUsuarioId(Long usuarioId);
}
