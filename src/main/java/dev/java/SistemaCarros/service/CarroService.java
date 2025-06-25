package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.CarroResponseDTO;
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

    @Autowired
    public CarroService(CarroRepository carroRepository, UsuarioRepository usuarioRepository){
        this.carroRepository = carroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<CarroResponseDTO> buscarTodosCarrosPorUsuario(Long usuarioId){
        List<Carro> carros = carroRepository.findByUsuarioId(usuarioId);

        return carros
                .stream()
                .map(this::carroParaResponseDTO)
                .toList();
    }

    public CarroResponseDTO buscarPorId(Long id, Long carroId){
        Carro carro = carroRepository.findById(carroId)
                .filter(c -> c.getUsuario().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado!"));

        return carroParaResponseDTO(carro);
    }

    public CarroResponseDTO criarCarro(Long id, CarroRequestDTO carroDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Carro carro = requestCarroParaEntidade(carroDTO);
        carro.setUsuario(usuario);

        Carro carroSalvo = carroRepository.save(carro);
        return carroParaResponseDTO(carroSalvo);
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
        return carroParaResponseDTO(carroAtualizado);
    }

    public CarroResponseDTO carroParaResponseDTO(Carro carro){
        if (carro.getUsuario() == null) {
            throw new RuntimeException("Carro sem usuário vinculado.");
        }

        return new CarroResponseDTO(
                carro.getPlaca(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabricacao(),
                carro.getCor(),
                carro.getTipoCombustivel(),
                carro.getTransmissao(),
                carro.getUsuario().getCpf()
        );
    }

    public Carro requestCarroParaEntidade(CarroRequestDTO carroDTO){
        Carro carro = new Carro();

        carro.setId(carroDTO.getId());
        carro.setPlaca(carroDTO.getPlaca());
        carro.setModelo(carroDTO.getModelo());
        carro.setMarca(carroDTO.getMarca());
        carro.setCor(carroDTO.getCor());
        carro.setAnoFabricacao(carroDTO.getAnoFabricacao());
        carro.setTipoCombustivel(carroDTO.getTipoCombustivel());
        carro.setTransmissao(carroDTO.getTransmissao());

        return carro;
    }
}
