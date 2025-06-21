package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.model.HorarioFuncionamento;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.ServicoRealizado;
import dev.java.SistemaCarros.repository.MecanicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Mecanica buscarPorId(Long id){
        return mecanicaRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));
    }

    public Mecanica criarMecanica(Mecanica mecanica) {
        if (mecanica.getServicos() != null){
            for (ServicoRealizado servico : mecanica.getServicos()){
                servico.setMecanica(mecanica);
            }
        }
        return mecanicaRepository.save(mecanica);
    }

    public void deletarMecanicaPorId(Long id){
        mecanicaRepository.deleteById(id);
    }
    public Mecanica atualizarMecanica(Long id, Mecanica mecanicaRequest){
        Mecanica mecanica = buscarPorId(id);

        mecanica.setNome(mecanicaRequest.getNome());
        mecanica.setCnpj(mecanicaRequest.getCnpj());
        mecanica.setEmail(mecanicaRequest.getEmail());
        mecanica.setEndereco(mecanicaRequest.getEndereco());
        mecanica.setHorarios(mecanicaRequest.getHorarios());
        mecanica.getEspecialidades().clear();
        mecanica.getServicos().clear();
        mecanica.setEspecialidades(mecanicaRequest.getEspecialidades());
        mecanica.setServicos(mecanicaRequest.getServicos());

        return mecanicaRepository.save(mecanica);
    }
}
