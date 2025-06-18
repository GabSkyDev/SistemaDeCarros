package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorCPF(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> buscarPorNome(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> buscarPorTelefone(String telefone){
        return usuarioRepository.findByTelefone(telefone);
    }

}
