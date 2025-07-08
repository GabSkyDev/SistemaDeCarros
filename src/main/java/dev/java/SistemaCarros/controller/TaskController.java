package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.TaskRequestDTO;
import dev.java.SistemaCarros.dto.TaskResponseDTO;
import dev.java.SistemaCarros.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService servicoService;

    @Autowired
    public TaskController(TaskService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarServicos() {
        List<TaskResponseDTO> servicos = servicoService.listarServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> buscarPorId(@PathVariable Long id) {
        TaskResponseDTO servico = servicoService.buscarPorId(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> criarServico(@RequestBody TaskRequestDTO dto) {
        TaskResponseDTO novoServico = servicoService.criarServico(dto);
        return new ResponseEntity<>(novoServico, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> atualizarServico(@PathVariable Long id, @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO servicoAtualizado = servicoService.atualizarServico(id, dto);
        return new ResponseEntity<>(servicoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
