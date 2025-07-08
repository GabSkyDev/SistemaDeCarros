package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.TaskResponseDTO;
import dev.java.SistemaCarros.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public static TaskResponseDTO toResponseDTO(Task servico){
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setDescricao(servico.getDescricao());
        dto.setDataServico(servico.getDataServico());
        dto.setValorPago(servico.getValorPago());
        dto.setMecanicaId(servico.getMechanical().getId());
        dto.setMecanicaNome(servico.getMechanical().getNome());
        if (servico.getCar() != null) {
            dto.setCarroId(servico.getCar().getId());
            dto.setPlacaCarro(servico.getCar().getPlaca());
            dto.setModeloCarro(servico.getCar().getModelo());
        }
        return dto;
    }
}
