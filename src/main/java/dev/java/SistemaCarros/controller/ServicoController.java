package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.ServicoRequestDTO;
import dev.java.SistemaCarros.dto.ServicoResponseDTO;
import dev.java.SistemaCarros.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarServicos() {
        List<ServicoResponseDTO> servicos = servicoService.listarServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarPorId(@PathVariable Long id) {
        ServicoResponseDTO servico = servicoService.buscarPorId(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criarServico(@RequestBody ServicoRequestDTO dto) {
        ServicoResponseDTO novoServico = servicoService.criarServico(dto);
        return new ResponseEntity<>(novoServico, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizarServico(@PathVariable Long id, @RequestBody ServicoRequestDTO dto) {
        ServicoResponseDTO servicoAtualizado = servicoService.atualizarServico(id, dto);
        return new ResponseEntity<>(servicoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
