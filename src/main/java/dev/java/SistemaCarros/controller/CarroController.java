package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.CarroResponseDTO;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/carros")
public class CarroController {
    @Autowired
    CarroService carroService;

    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listarTodos(@PathVariable Long usuarioId){
        List<CarroResponseDTO> carros = carroService.buscarTodosCarrosPorUsuario(usuarioId);
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/{carroId}")
    public ResponseEntity<CarroResponseDTO>  buscarPorId(@PathVariable Long usuarioId, @PathVariable Long carroId){
        CarroResponseDTO carro = carroService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarroResponseDTO> criarCarro(@PathVariable Long usuarioId, @RequestBody CarroRequestDTO carroRequest){
        CarroResponseDTO novoCarro = carroService.criarCarro(usuarioId, carroRequest);
        return new ResponseEntity<>(novoCarro, HttpStatus.OK);
    }

    @DeleteMapping("/{carroId}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long usuarioId, @PathVariable Long carroId){
        carroService.deletarCarro(usuarioId, carroId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{carroId}")
    public ResponseEntity<CarroResponseDTO> atualizarCarro(@PathVariable Long usuarioId, @PathVariable Long carroId, @RequestBody CarroRequestDTO carroRequest){
        carroService.atualizarCarro(usuarioId, carroId, carroRequest);
        CarroResponseDTO carro = carroService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }
}
