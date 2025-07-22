package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.ClientWithCarsDTO;
import dev.java.SistemaCarros.dto.ClientRequestDTO;
import dev.java.SistemaCarros.dto.ClientResponseDTO;
import dev.java.SistemaCarros.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientRequestDTO>> listarTodos(){
        List<ClientRequestDTO> ListaUsuarios = clientService.buscarTodosUsuarios();
        return new ResponseEntity<>(ListaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> buscarPorId(@PathVariable Long id){
        ClientResponseDTO usuario = clientService.buscarPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<ClientWithCarsDTO> buscarPorIdComCarro(@PathVariable Long id){
        ClientWithCarsDTO usuario = clientService.buscarUsuarioComCarro(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<ClientWithCarsDTO>> listarTodosComCarro(){
        List<ClientWithCarsDTO> usuarios = clientService.buscarTodosUsuariosComCarro();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> criarUsuario(@RequestBody ClientRequestDTO usuario){
        ClientResponseDTO novoUsuario = clientService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        clientService.deletarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody ClientRequestDTO novoUsuario){
         clientService.atualizarUsuario(id, novoUsuario);
         ClientResponseDTO usuario = clientService.buscarPorId(id);
         return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
