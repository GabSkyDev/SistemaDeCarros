package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.CarroResponseDTO;
import dev.java.SistemaCarros.mapper.CarroMapper;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Usuario;
import dev.java.SistemaCarros.repository.CarroRepository;
import dev.java.SistemaCarros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final UsuarioRepository usuarioRepository;
    private CarroMapper carroMapper;
    @Autowired
    public CarroService(CarroRepository carroRepository, UsuarioRepository usuarioRepository, CarroMapper carroMapper){
        this.carroRepository = carroRepository;
        this.usuarioRepository = usuarioRepository;
        this.carroMapper = carroMapper;
    }

    public List<CarroResponseDTO> buscarTodosCarrosPorUsuario(Long usuarioId){
        List<Carro> carros = carroRepository.findByUsuarioId(usuarioId);

        return carros
                .stream()
                .map(CarroMapper::toResponseDTO)
                .toList();
    }

    public CarroResponseDTO buscarPorId(Long id, Long carroId){
        Carro carro = carroRepository.findById(carroId)
                .filter(c -> c.getUsuario().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado!"));

        return carroMapper.toResponseDTO(carro);
    }

    public CarroResponseDTO criarCarro(Long id, CarroRequestDTO carroDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Carro carro = carroMapper.toEntidade(carroDTO);
        carro.setUsuario(usuario);

        Carro carroSalvo = carroRepository.save(carro);
        return carroMapper.toResponseDTO(carroSalvo);
    }

    public void deletarCarro(Long id, Long carroId){
        Carro carro = carroRepository.findById(carroId)
                .filter(c -> c.getUsuario().getId().equals(id))
                .orElseThrow(() -> new RuntimeException("Carro não encontrado para este usuário"));

        carroRepository.delete(carro);
    }

    public CarroResponseDTO atualizarCarro(Long id, Long carroId, CarroRequestDTO carroRequest){
        Carro carro = carroRepository.findById(carroId)
                .filter(c -> c.getUsuario().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado para este usuário"));

        carro.setPlaca(carroRequest.getPlaca());
        carro.setMarca(carroRequest.getMarca());
        carro.setModelo(carroRequest.getModelo());
        carro.setCor(carroRequest.getCor());
        carro.setAnoFabricacao(carroRequest.getAnoFabricacao());
        carro.setTipoCombustivel(carroRequest.getTipoCombustivel());
        carro.setTransmissao(carroRequest.getTransmissao());

        Carro carroAtualizado = carroRepository.save(carro);
        return carroMapper.toResponseDTO(carroAtualizado);
    }
}
