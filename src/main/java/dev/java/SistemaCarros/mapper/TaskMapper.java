package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.TaskResponseDTO;
import dev.java.SistemaCarros.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponseDTO toResponseDTO(Task servico){
        TaskResponseDTO dto = new TaskResponseDTO(
                servico.getDescricao(),
                servico.getDataServico(),
                servico.getValorPago(),
                servico.getMechanical().getId(),
                servico.getMechanical().getNome(),
                servico.getCar().getId(),
                servico.getCar().getPlaca(),
                servico.getCar().getModelo()

        );

        return dto;
    }
}
