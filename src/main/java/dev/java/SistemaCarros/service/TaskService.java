package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.TaskRequestDTO;
import dev.java.SistemaCarros.dto.TaskResponseDTO;
import dev.java.SistemaCarros.mapper.TaskMapper;
import dev.java.SistemaCarros.model.Car;
import dev.java.SistemaCarros.model.Mechanical;
import dev.java.SistemaCarros.model.Task;
import dev.java.SistemaCarros.repository.CarRepository;
import dev.java.SistemaCarros.repository.MechanicalRepository;
import dev.java.SistemaCarros.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final MechanicalRepository mechanicalRepository;
    private final CarRepository carRepository;
    private TaskMapper taskMapper;
    @Autowired
    public TaskService(TaskRepository taskRepository, MechanicalRepository mechanicalRepository, CarRepository carRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.mechanicalRepository = mechanicalRepository;
        this.carRepository = carRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponseDTO> listarServicos() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    public TaskResponseDTO buscarPorId(Long id) {
        Task servico = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return taskMapper.toResponseDTO(servico);
    }

    public TaskResponseDTO criarServico(TaskRequestDTO servicoDTO) {
        Mechanical mecanica = mechanicalRepository.findById(servicoDTO.mecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Car car = null;
        if (servicoDTO.carroId() != null) {
            car = carRepository.findById(servicoDTO.carroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        Task servico = new Task();
        servico.setDescricao(servicoDTO.descricao());
        servico.setDataServico(servicoDTO.dataServico());
        servico.setValorPago(servicoDTO.valorPago());
        servico.setMechanical(mecanica);
        servico.setCar(car);
        taskRepository.save(servico);

        return taskMapper.toResponseDTO(servico);
    }

    public void deletarServico(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        taskRepository.deleteById(id);
    }


    public TaskResponseDTO atualizarServico(Long id, TaskRequestDTO dto) {
        Task servico = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Mechanical mecanica = mechanicalRepository.findById(dto.mecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Car car = null;
        if (dto.carroId() != null) {
            car = carRepository.findById(dto.carroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        servico.setDescricao(dto.descricao());
        servico.setDataServico(dto.dataServico());
        servico.setValorPago(dto.valorPago());
        servico.setMechanical(mecanica);
        servico.setCar(car);

        return taskMapper.toResponseDTO(taskRepository.save(servico));
    }

}
