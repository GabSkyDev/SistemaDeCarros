package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.MecanicaRequestDTO;
import dev.java.SistemaCarros.dto.MecanicaResponseDTO;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.Servico;
import dev.java.SistemaCarros.repository.MecanicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicaService {

    private MecanicaRepository mecanicaRepository;

    @Autowired
    public MecanicaService(MecanicaRepository mecanicaRepository){
        this.mecanicaRepository = mecanicaRepository;
    }

    public List<MecanicaResponseDTO> buscarTodasMecanicas(){
        List<Mecanica> mecanica = mecanicaRepository.findAll();

        return mecanica
                .stream()
                .map(this::mecanicaParaResponseDTO)
                .toList();
    }

    public MecanicaResponseDTO buscarPorId(Long id){
        Mecanica mecanica = mecanicaRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));

        return mecanicaParaResponseDTO(mecanica);

    }

    public MecanicaResponseDTO criarMecanica(MecanicaRequestDTO mecanicaDTO) {
        Mecanica mecanica = requestMecanicaParaEntidade(mecanicaDTO);

        mecanica.getServicos().forEach(servico -> servico.setMecanica(mecanica));

        Mecanica mecanicaSalva = mecanicaRepository.save(mecanica);
        return mecanicaParaResponseDTO(mecanicaSalva);
    }

    public void deletarMecanicaPorId(Long id){
        if (!mecanicaRepository.existsById(id)) {
            throw new RuntimeException("Mecânica não encontrada");
        }
        mecanicaRepository.deleteById(id);
    }
    public MecanicaResponseDTO atualizarMecanica(Long id, MecanicaRequestDTO mecanicaRequest){
        Mecanica mecanica = mecanicaRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));;

        mecanica.setNome(mecanicaRequest.getNome());
        mecanica.setCnpj(mecanicaRequest.getCnpj());
        mecanica.setEmail(mecanicaRequest.getEmail());
        mecanica.setEndereco(mecanicaRequest.getEndereco());
        mecanica.setHorarios(mecanicaRequest.getHorarios());
        mecanica.setEspecialidades(mecanicaRequest.getEspecialidades());

        mecanica.getEspecialidades().clear();
        List<Servico> servicos = mecanicaRequest.getServicos().stream().map(mecDTO -> {
            Servico servico = new Servico();
            servico.setId(mecDTO.getId());
            servico.setDataServico(mecDTO.getDataServico());
            servico.setCarro(mecDTO.getCarro());
            servico.setValorPago(mecDTO.getValorPago());
            servico.setDescricao(mecDTO.getDescricao());
            servico.setMecanica(mecanica);
            return servico;
        }).toList();

        mecanica.getServicos().addAll(servicos);

        Mecanica mecanicaAtualizada = mecanicaRepository.save(mecanica);

        return mecanicaParaResponseDTO(mecanicaAtualizada);
    }

    public MecanicaResponseDTO mecanicaParaResponseDTO(Mecanica mecanica){
        return new MecanicaResponseDTO(
                mecanica.getCnpj(),
                mecanica.getNome(),
                mecanica.getEndereco(),
                mecanica.getEmail(),
                mecanica.getTelefone(),
                mecanica.getEspecialidades(),
                mecanica.getHorarios()
        );
    }

    private Mecanica requestMecanicaParaEntidade(MecanicaRequestDTO mecanicaDTO){
        Mecanica mecanica = new Mecanica();

        mecanica.setId(mecanicaDTO.getId());
        mecanica.setCnpj(mecanicaDTO.getCnpj());
        mecanica.setNome(mecanicaDTO.getNome());
        mecanica.setEndereco(mecanicaDTO.getEndereco());
        mecanica.setEmail(mecanicaDTO.getEmail());
        mecanica.setEspecialidades(mecanicaDTO.getEspecialidades());
        mecanica.setHorarios(mecanicaDTO.getHorarios());

        List<Servico> servicos = mecanicaDTO.getServicos().stream().map(mecDTO -> {
            Servico servico = new Servico();
            servico.setId(mecDTO.getId());
            servico.setDataServico(mecDTO.getDataServico());
            servico.setCarro(mecDTO.getCarro());
            servico.setValorPago(mecDTO.getValorPago());
            servico.setDescricao(mecDTO.getDescricao());
            servico.setMecanica(mecanica);
            return servico;
        }).toList();

        mecanica.setServicos(servicos);

        return mecanica;
    }
}
