package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.UsuarioComCarrosDTO;
import dev.java.SistemaCarros.dto.UsuarioRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioResponseDTO;
import dev.java.SistemaCarros.mapper.UsuarioMapper;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioRequestDTO> buscarTodosUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios
                .stream()
                .map(UsuarioMapper::toRequestDTO)
                .toList();
    }

    public UsuarioComCarrosDTO buscarUsuarioComCarro(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));
        return usuarioMapper.toUsuarioComCarrosDTO(usuario);
    }

    public List<UsuarioComCarrosDTO> buscarTodosUsuariosComCarro(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios
                .stream()
                .map(UsuarioMapper::toUsuarioComCarrosDTO)
                .toList();

    }
    public UsuarioResponseDTO buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));

        return usuarioMapper.toResponseDTO(usuario);
    }
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO){
        Usuario usuario = usuarioMapper.toEntidade(usuarioDTO);

        usuario.getCarrosRegistrados().forEach(carro -> carro.setUsuario (usuario));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuarioSalvo);
    }
    public void deletarUsuarioPorId(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequest){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setTelefone(usuarioRequest.getTelefone());

        usuario.getCarrosRegistrados().clear();

        List<Carro> carros = usuarioRequest.getCarrosRegistrados().stream().map(carroDTO -> {
            Carro carro = new Carro();
            carro.setPlaca(carroDTO.getPlaca());
            carro.setModelo(carroDTO.getModelo());
            carro.setMarca(carroDTO.getMarca());
            carro.setAnoFabricacao(carroDTO.getAnoFabricacao());
            carro.setCor(carroDTO.getCor());
            carro.setTipoCombustivel(carroDTO.getTipoCombustivel());
            carro.setTransmissao(carroDTO.getTransmissao());
            carro.setUsuario(usuario);
            return carro;
        }).toList();

        usuario.getCarrosRegistrados().addAll(carros);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuarioAtualizado);
    }
}
