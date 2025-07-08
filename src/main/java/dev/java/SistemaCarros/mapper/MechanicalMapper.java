package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.MechanicalRequestDTO;
import dev.java.SistemaCarros.dto.MechanicalResponseDTO;
import dev.java.SistemaCarros.model.Mechanical;
import dev.java.SistemaCarros.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MechanicalMapper {

    public static MechanicalResponseDTO toResponseDTO(Mechanical mecanica){
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

    public static Mechanical toEntidade(MechanicalRequestDTO mecanicaDTO){
        Mechanical mecanica = new Mechanical();

        mecanica.setId(mecanicaDTO.getId());
        mecanica.setCnpj(mecanicaDTO.getCnpj());
        mecanica.setNome(mecanicaDTO.getNome());
        mecanica.setEndereco(mecanicaDTO.getEndereco());
        mecanica.setEmail(mecanicaDTO.getEmail());
        mecanica.setEspecialidades(mecanicaDTO.getEspecialidades());
        mecanica.setHorarios(mecanicaDTO.getHorarios());

        List<Task> servicos = mecanicaDTO.getServicos().stream().map(mecDTO -> {
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
