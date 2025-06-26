package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.ServicoResponseDTO;
import dev.java.SistemaCarros.model.Servico;

public class ServicoMapper {
    public static ServicoResponseDTO toResponseDTO(Servico servico){
        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.setDescricao(servico.getDescricao());
        dto.setDataServico(servico.getDataServico());
        dto.setValorPago(servico.getValorPago());
        dto.setMecanicaId(servico.getMecanica().getId());
        dto.setMecanicaNome(servico.getMecanica().getNome());
        if (servico.getCarro() != null) {
            dto.setCarroId(servico.getCarro().getId());
            dto.setPlacaCarro(servico.getCarro().getPlaca());
            dto.setModeloCarro(servico.getCarro().getModelo());
        }
        return dto;
    }
}
