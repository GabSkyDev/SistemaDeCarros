package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List> listarTodos(){
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario){
         usuarioService.atualizarUsuario(id, novoUsuario);
         Usuario usuario = usuarioService.buscarPorId(id);
         return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
