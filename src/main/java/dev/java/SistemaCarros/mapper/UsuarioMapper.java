package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioComCarrosDTO;
import dev.java.SistemaCarros.dto.UsuarioRequestDTO;
import dev.java.SistemaCarros.dto.UsuarioResponseDTO;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Usuario;

import java.util.List;

public class UsuarioMapper {
    public static UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

    public static UsuarioRequestDTO toRequestDTO(Usuario usuario){
        return new UsuarioRequestDTO(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

    public static UsuarioComCarrosDTO toUsuarioComCarrosDTO(Usuario usuario){
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

    public static Usuario toEntidade(UsuarioRequestDTO usuarioDTO){
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
