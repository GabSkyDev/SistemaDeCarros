package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.MechanicalRequestDTO;
import dev.java.SistemaCarros.dto.MechanicalResponseDTO;
import dev.java.SistemaCarros.mapper.MechanicalMapper;
import dev.java.SistemaCarros.model.Mechanical;
import dev.java.SistemaCarros.model.Task;
import dev.java.SistemaCarros.repository.MechanicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicalService {

    private MechanicalRepository mecanicaRepository;
    private MechanicalMapper mecanicaMapper;

    @Autowired
    public MechanicalService(MechanicalRepository mecanicaRepository, MechanicalMapper mecanicaMapper){
        this.mecanicaRepository = mecanicaRepository;
        this.mecanicaMapper = mecanicaMapper;
    }

    public List<MechanicalResponseDTO> buscarTodasMecanicas(){
        List<Mechanical> mecanica = mecanicaRepository.findAll();

        return mecanica
                .stream()
                .map(MechanicalMapper::toResponseDTO)
                .toList();
    }

    public MechanicalResponseDTO buscarPorId(Long id){
        Mechanical mecanica = mecanicaRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));

        return mecanicaMapper.toResponseDTO(mecanica);

    }

    public MechanicalResponseDTO criarMecanica(MechanicalRequestDTO mecanicaDTO) {
        Mechanical mecanica = mecanicaMapper.toEntidade(mecanicaDTO);

        mecanica.getServicos().forEach(servico -> servico.setMechanical(mecanica));

        Mechanical mecanicaSalva = mecanicaRepository.save(mecanica);
        return mecanicaMapper.toResponseDTO(mecanicaSalva);
    }

    public void deletarMecanicaPorId(Long id){
        if (!mecanicaRepository.existsById(id)) {
            throw new RuntimeException("Mecânica não encontrada");
        }
        mecanicaRepository.deleteById(id);
    }
    public MechanicalResponseDTO atualizarMecanica(Long id, MechanicalRequestDTO mecanicaRequest){
        Mechanical mecanica = mecanicaRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));;

        mecanica.setNome(mecanicaRequest.getNome());
        mecanica.setCnpj(mecanicaRequest.getCnpj());
        mecanica.setEmail(mecanicaRequest.getEmail());
        mecanica.setEndereco(mecanicaRequest.getEndereco());
        mecanica.setHorarios(mecanicaRequest.getHorarios());
        mecanica.setEspecialidades(mecanicaRequest.getEspecialidades());

        mecanica.getEspecialidades().clear();
        List<Task> servicos = mecanicaRequest.getServicos().stream().map(mecDTO -> {
            Task servico = new Task();
            servico.setId(mecDTO.getId());
            servico.setDataServico(mecDTO.getDataServico());
            servico.setCar(mecDTO.getCar());
            servico.setValorPago(mecDTO.getValorPago());
            servico.setDescricao(mecDTO.getDescricao());
            servico.setMechanical(mecanica);
            return servico;
        }).toList();

        mecanica.getServicos().addAll(servicos);

        Mechanical mecanicaAtualizada = mecanicaRepository.save(mecanica);

        return mecanicaMapper.toResponseDTO(mecanicaAtualizada);
    }
}
