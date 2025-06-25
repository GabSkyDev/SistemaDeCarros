package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.UsuarioComCarrosDTO;
import dev.java.SistemaCarros.dto.UsuarioRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioResponseDTO;
import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioRequestDTO>> listarTodos(){
        List<UsuarioRequestDTO> ListaUsuarios = usuarioService.buscarTodosUsuarios();
        return new ResponseEntity<>(ListaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/carro/{id}")
    public ResponseEntity<UsuarioComCarrosDTO> buscarPorIdComCarro(@PathVariable Long id){
        UsuarioComCarrosDTO usuario = usuarioService.buscarUsuarioComCarro(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/carro")
    public ResponseEntity<List<UsuarioComCarrosDTO>> listarTodosComCarro(){
        List<UsuarioComCarrosDTO> usuarios = usuarioService.buscarTodosUsuariosComCarro();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO usuario){
        UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO novoUsuario){
         usuarioService.atualizarUsuario(id, novoUsuario);
         UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
         return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
