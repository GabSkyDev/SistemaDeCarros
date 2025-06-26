package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.MecanicaRequestDTO;
import dev.java.SistemaCarros.dto.MecanicaResponseDTO;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.Servico;

import java.util.List;

public class MecanicaMapper {
    public static MecanicaResponseDTO toResponseDTO(Mecanica mecanica){
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

    public static Mecanica toEntidade(MecanicaRequestDTO mecanicaDTO){
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
