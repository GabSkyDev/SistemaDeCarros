package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.model.HorarioFuncionamento;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.ServicoRealizado;
import dev.java.SistemaCarros.repository.MecanicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MecanicaService {

    private MecanicaRepository mecanicaRepository;

    @Autowired
    public MecanicaService(MecanicaRepository mecanicaRepository){
        this.mecanicaRepository = mecanicaRepository;
    }

    public List<Mecanica> buscarTodasMecanicas(){
        return mecanicaRepository.findAll();
    }

    public Optional<Mecanica> buscarPorId(Long id){
        return mecanicaRepository.findById(id);
    }

    public Optional<Mecanica> buscarPorCNPJ(String cnpj){
        return mecanicaRepository.findByCnpj(cnpj);
    }

    public List<Mecanica> buscarPorNome(String nome){
        return mecanicaRepository.findByNome(nome);
    }

    public List<Mecanica> buscarPorEndereco(String endereco){
        return mecanicaRepository.findByEndereco(endereco);
    }

    public Optional<Mecanica> buscarPorEmail(String email){
        return mecanicaRepository.findByEmail(email);
    }

    public Optional<Mecanica> buscarPorTelefone(String telefone){
        return mecanicaRepository.findByTelefone(telefone);
    }
    public List<Mecanica> buscarEspecialidadesPorId(Long idMecanica){
        return mecanicaRepository.findEspecialidadeById(idMecanica);
    }

    public Optional<HorarioFuncionamento> buscarHorarioPorId(Long mecanicaId){
        return mecanicaRepository.findHorarioMecanicaById(mecanicaId);
    }

    public List<ServicoRealizado> buscarServicoPorId(Long idMecanica){
        return mecanicaRepository.findServicoById(idMecanica);
    }
}
