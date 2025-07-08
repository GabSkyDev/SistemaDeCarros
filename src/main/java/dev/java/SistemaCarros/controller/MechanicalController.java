package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.MechanicalRequestDTO;
import dev.java.SistemaCarros.dto.MechanicalResponseDTO;
import dev.java.SistemaCarros.service.MechanicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mechanical")
public class MechanicalController {
    @Autowired
    MechanicalService mechanicalService;

    @GetMapping
    public ResponseEntity<List<MechanicalResponseDTO>> listarTodos(){
        List<MechanicalResponseDTO> mecanicas = mechanicalService.buscarTodasMecanicas();
        return new ResponseEntity<>(mecanicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanicalResponseDTO> buscarPorId(@PathVariable Long id){
        MechanicalResponseDTO mecanica = mechanicalService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MechanicalResponseDTO> criarMecanica(@RequestBody MechanicalRequestDTO mecanica){
        MechanicalResponseDTO novaMecanica = mechanicalService.criarMecanica(mecanica);
        return new ResponseEntity<>(novaMecanica,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMecanica(@PathVariable Long id){
        mechanicalService.deletarMecanicaPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MechanicalResponseDTO> atualizarMecanica(@PathVariable Long id, @RequestBody MechanicalRequestDTO mecanicaRequest){
        mechanicalService.atualizarMecanica(id, mecanicaRequest);
        MechanicalResponseDTO mecanica = mechanicalService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }



}
