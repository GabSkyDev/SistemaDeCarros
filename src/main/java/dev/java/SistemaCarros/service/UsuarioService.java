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
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));
    }
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void deletarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioRequest){
        Usuario usuario = buscarPorId(id);

        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setTelefone(usuarioRequest.getTelefone());
        usuario.getCarrosRegistrados().clear();
        usuario.getCarrosRegistrados().addAll(usuarioRequest.getCarrosRegistrados());

        return usuarioRepository.save(usuario);
    }
}
