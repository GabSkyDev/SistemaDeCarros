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
    private final TaskRepository servicoRepository;
    private final MechanicalRepository mecanicaRepository;
    private final CarRepository carroRepository;
    private TaskMapper servicoMapper;
    @Autowired
    public TaskService(TaskRepository servicoRepository, MechanicalRepository mecanicaRepository, CarRepository carroRepository, TaskMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.mecanicaRepository = mecanicaRepository;
        this.carroRepository = carroRepository;
        this.servicoMapper = servicoMapper;
    }

    public List<TaskResponseDTO> listarServicos() {
        return servicoRepository.findAll().stream()
                .map(TaskMapper::toResponseDTO)
                .toList();
    }

    public TaskResponseDTO buscarPorId(Long id) {
        Task servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return servicoMapper.toResponseDTO(servico);
    }

    public TaskResponseDTO criarServico(TaskRequestDTO servicoDTO) {
        Mechanical mecanica = mecanicaRepository.findById(servicoDTO.getMecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Car car = null;
        if (servicoDTO.getCarroId() != null) {
            car = carroRepository.findById(servicoDTO.getCarroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        Task servico = new Task();
        servico.setDescricao(servicoDTO.getDescricao());
        servico.setDataServico(servicoDTO.getDataServico());
        servico.setValorPago(servicoDTO.getValorPago());
        servico.setMechanical(mecanica);
        servico.setCar(car);
        servicoRepository.save(servico);

        return servicoMapper.toResponseDTO(servico);
    }

    public void deletarServico(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        servicoRepository.deleteById(id);
    }


    public TaskResponseDTO atualizarServico(Long id, TaskRequestDTO dto) {
        Task servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Mechanical mecanica = mecanicaRepository.findById(dto.getMecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Car car = null;
        if (dto.getCarroId() != null) {
            car = carroRepository.findById(dto.getCarroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        servico.setDescricao(dto.getDescricao());
        servico.setDataServico(dto.getDataServico());
        servico.setValorPago(dto.getValorPago());
        servico.setMechanical(mecanica);
        servico.setCar(car);

        return servicoMapper.toResponseDTO(servicoRepository.save(servico));
    }

}
