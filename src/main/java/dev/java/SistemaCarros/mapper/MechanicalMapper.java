package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.MechanicalRequestDTO;
import dev.java.SistemaCarros.dto.MechanicalResponseDTO;
import dev.java.SistemaCarros.model.Mechanical;
import dev.java.SistemaCarros.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MechanicalMapper {

    public MechanicalResponseDTO toResponseDTO(Mechanical mecanica){
        return new MechanicalResponseDTO(
                mecanica.getCnpj(),
                mecanica.getNome(),
                mecanica.getEndereco(),
                mecanica.getEmail(),
                mecanica.getTelefone(),
                mecanica.getEspecialidades(),
                mecanica.getHorarios()
        );
    }

    public Mechanical toEntity(MechanicalRequestDTO mecanicaDTO){
        Mechanical mecanica = new Mechanical();

        mecanica.setId(mecanicaDTO.id());
        mecanica.setCnpj(mecanicaDTO.cnpj());
        mecanica.setNome(mecanicaDTO.nome());
        mecanica.setEndereco(mecanicaDTO.endereco());
        mecanica.setEmail(mecanicaDTO.email());
        mecanica.setEspecialidades(mecanicaDTO.especialidades());
        mecanica.setHorarios(mecanicaDTO.horarios());

        List<Task> servicos = mecanicaDTO.servicos().stream().map(mecDTO -> {
            Task servico = new Task();
            servico.setId(mecDTO.getId());
            servico.setDataServico(mecDTO.getDataServico());
            servico.setCar(mecDTO.getCar());
            servico.setValorPago(mecDTO.getValorPago());
            servico.setDescricao(mecDTO.getDescricao());
            servico.setMechanical(mecanica);
            return servico;
        }).toList();

        mecanica.setServicos(servicos);

        return mecanica;
    }
}
