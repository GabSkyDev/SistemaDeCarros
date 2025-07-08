package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.CarRequestDTO;
import dev.java.SistemaCarros.dto.CarResponseDTO;
import dev.java.SistemaCarros.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/{usuarioId}/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> listarTodos(@PathVariable Long usuarioId){
        List<CarResponseDTO> carros = carService.buscarTodosCarrosPorUsuario(usuarioId);
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/{carroId}")
    public ResponseEntity<CarResponseDTO>  buscarPorId(@PathVariable Long usuarioId, @PathVariable Long carroId){
        CarResponseDTO carro = carService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> criarCarro(@PathVariable Long usuarioId, @RequestBody CarRequestDTO carroRequest){
        CarResponseDTO novoCarro = carService.criarCarro(usuarioId, carroRequest);
        return new ResponseEntity<>(novoCarro, HttpStatus.OK);
    }

    @DeleteMapping("/{carroId}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long usuarioId, @PathVariable Long carroId){
        carService.deletarCarro(usuarioId, carroId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{carroId}")
    public ResponseEntity<CarResponseDTO> atualizarCarro(@PathVariable Long usuarioId, @PathVariable Long carroId, @RequestBody CarRequestDTO carroRequest){
        carService.atualizarCarro(usuarioId, carroId, carroRequest);
        CarResponseDTO carro = carService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }
}
