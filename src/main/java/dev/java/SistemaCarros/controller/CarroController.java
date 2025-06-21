package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    CarroService carroService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List> listarTodos(){
        List<Carro> carros = carroService.buscarTodosCarros();
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Carro> listarCarroPorId(@PathVariable Long id){
        Carro carro = carroService.buscarPorId(id);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro carro){
        Carro novoCarro = carroService.criarCarro(carro);
        return new ResponseEntity<>(novoCarro, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id){
        carroService.deletarCarro(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroRequest){
        carroService.atualizarCarro(id, carroRequest);
        Carro carro = carroService.buscarPorId(id);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }
}
