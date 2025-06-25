package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.MecanicaRequestDTO;
import dev.java.SistemaCarros.dto.MecanicaResponseDTO;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.service.MecanicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mecanica")
public class MecanicaController {
    @Autowired
    MecanicaService mecanicaService;

    @GetMapping
    public ResponseEntity<List<MecanicaResponseDTO>> listarTodos(){
        List<MecanicaResponseDTO> mecanicas = mecanicaService.buscarTodasMecanicas();
        return new ResponseEntity<>(mecanicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MecanicaResponseDTO> buscarPorId(@PathVariable Long id){
        MecanicaResponseDTO mecanica = mecanicaService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MecanicaResponseDTO> criarMecanica(@RequestBody MecanicaRequestDTO mecanica){
        MecanicaResponseDTO novaMecanica = mecanicaService.criarMecanica(mecanica);
        return new ResponseEntity<>(novaMecanica,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMecanica(@PathVariable Long id){
        mecanicaService.deletarMecanicaPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MecanicaResponseDTO> atualizarMecanica(@PathVariable Long id, @RequestBody MecanicaRequestDTO mecanicaRequest){
        mecanicaService.atualizarMecanica(id, mecanicaRequest);
        MecanicaResponseDTO mecanica = mecanicaService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }



}
