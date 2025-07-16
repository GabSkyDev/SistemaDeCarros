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

    private MechanicalRepository mechanicalRepository;
    private MechanicalMapper mechanicalMapper;

    @Autowired
    public MechanicalService(MechanicalRepository mechanicalRepository, MechanicalMapper mechanicalMapper){
        this.mechanicalRepository = mechanicalRepository;
        this.mechanicalMapper = mechanicalMapper;
    }

    public List<MechanicalResponseDTO> buscarTodasMecanicas(){
        List<Mechanical> mecanica = mechanicalRepository.findAll();

        return mecanica
                .stream()
                .map(mechanicalMapper::toResponseDTO)
                .toList();
    }

    public MechanicalResponseDTO buscarPorId(Long id){
        Mechanical mecanica = mechanicalRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));

        return mechanicalMapper.toResponseDTO(mecanica);

    }

    public MechanicalResponseDTO criarMecanica(MechanicalRequestDTO mecanicaDTO) {
        Mechanical mecanica = mechanicalMapper.toEntity(mecanicaDTO);

        mecanica.getServicos().forEach(servico -> servico.setMechanical(mecanica));

        Mechanical mecanicaSalva = mechanicalRepository.save(mecanica);
        return mechanicalMapper.toResponseDTO(mecanicaSalva);
    }

    public void deletarMecanicaPorId(Long id){
        if (!mechanicalRepository.existsById(id)) {
            throw new RuntimeException("Mecânica não encontrada");
        }
        mechanicalRepository.deleteById(id);
    }
    public MechanicalResponseDTO atualizarMecanica(Long id, MechanicalRequestDTO mecanicaRequest){
        Mechanical mecanica = mechanicalRepository.findById(id)
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

        Mechanical mecanicaAtualizada = mechanicalRepository.save(mecanica);

        return mechanicalMapper.toResponseDTO(mecanicaAtualizada);
    }
}
