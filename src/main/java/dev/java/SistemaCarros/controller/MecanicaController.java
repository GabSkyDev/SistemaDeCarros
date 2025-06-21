package dev.java.SistemaCarros.controller;

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

    @GetMapping("/listarTodos")
    public ResponseEntity<List> listarTodos(){
        List<Mecanica> mecanicas = mecanicaService.buscarTodasMecanicas();
        return new ResponseEntity<>(mecanicas, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mecanica> buscarPorId(@PathVariable Long id){
        Mecanica mecanica = mecanicaService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<Mecanica> criarMecanica(@RequestBody Mecanica mecanica){
        Mecanica novaMecanica = mecanicaService.criarMecanica(mecanica);
        return new ResponseEntity<>(novaMecanica,HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMecanica(@PathVariable Long id){
        mecanicaService.deletarMecanicaPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Mecanica> atualizarMecanica(@PathVariable Long id, @RequestBody Mecanica mecanicaRequest){
        mecanicaService.atualizarMecanica(id, mecanicaRequest);
        Mecanica mecanica = mecanicaService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }



}
