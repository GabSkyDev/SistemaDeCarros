package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioComCarrosDTO;
import dev.java.SistemaCarros.dto.UsuarioRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioResponseDTO;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioRequestDTO> buscarTodosUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios
                .stream()
                .map(this::usuarioParaRequestDTO)
                .toList();
    }

    public UsuarioComCarrosDTO buscarUsuarioComCarro(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));
        return usuarioParaDTOComCarros(usuario);
    }

    public List<UsuarioComCarrosDTO> buscarTodosUsuariosComCarro(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios
                .stream()
                .map(this::usuarioParaDTOComCarros)
                .toList();

    }
    public UsuarioResponseDTO buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));

        return usuarioParaResponseDTO(usuario);
    }
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO){
        Usuario usuario = requestUsuarioParaEntidade(usuarioDTO);

        usuario.getCarrosRegistrados().forEach(carro -> carro.setUsuario (usuario));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioParaResponseDTO(usuarioSalvo);
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

        return usuarioParaResponseDTO(usuarioAtualizado);
    }


    public UsuarioComCarrosDTO usuarioParaDTOComCarros(Usuario usuario){
        List<CarroRequestDTO> carros = usuario.getCarrosRegistrados()
                .stream()
                .map(carro -> new CarroRequestDTO(
                        carro.getId(),
                        carro.getPlaca(),
                        carro.getModelo(),
                        carro.getMarca(),
                        carro.getAnoFabricacao(),
                        carro.getCor(),
                        carro.getTipoCombustivel(),
                        carro.getTransmissao(),
                        usuario.getCpf()
                ))
                .toList();

        return new UsuarioComCarrosDTO(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                carros
        );
    }

    public UsuarioRequestDTO usuarioParaRequestDTO(Usuario usuario){
        return new UsuarioRequestDTO(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

    public UsuarioResponseDTO usuarioParaResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

    private Usuario requestUsuarioParaEntidade(UsuarioRequestDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());

        List<Carro> carros = usuarioDTO.getCarrosRegistrados().stream().map(carroDTO ->{
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

        usuario.setCarrosRegistrados(carros);

        return usuario;
    }
}
