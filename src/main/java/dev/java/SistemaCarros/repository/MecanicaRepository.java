package dev.java.SistemaCarros.repository;

import dev.java.SistemaCarros.model.HorarioFuncionamento;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.ServicoRealizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface MecanicaRepository extends JpaRepository<Mecanica, Long> {
    Optional<Mecanica> findByCnpj(String cnpj);
    List<Mecanica> findByNome(String nome);
    List<Mecanica> findByEndereco(String endereco);
    Optional<Mecanica> findByEmail(String email);
    Optional<Mecanica> findByTelefone(String telefone);
    List<Mecanica> findEspecialidadeById(Long id);
    Optional<HorarioFuncionamento> findHorarioMecanicaById(Long mecanicaId);
    List<ServicoRealizado> findServicoById(Long idMecanica);

}