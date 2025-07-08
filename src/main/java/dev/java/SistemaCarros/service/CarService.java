package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.CarRequestDTO;
import dev.java.SistemaCarros.dto.CarResponseDTO;
import dev.java.SistemaCarros.mapper.CarMapper;
import dev.java.SistemaCarros.model.Car;
import dev.java.SistemaCarros.model.Client;
import dev.java.SistemaCarros.repository.CarRepository;
import dev.java.SistemaCarros.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carroRepository;
    private final ClientRepository usuarioRepository;
    private CarMapper carroMapper;
    @Autowired
    public CarService(CarRepository carroRepository, ClientRepository usuarioRepository, CarMapper carroMapper){
        this.carroRepository = carroRepository;
        this.usuarioRepository = usuarioRepository;
        this.carroMapper = carroMapper;
    }

    public List<CarResponseDTO> buscarTodosCarrosPorUsuario(Long usuarioId){
        List<Car> cars = carroRepository.findByClientId(usuarioId);

        return cars
                .stream()
                .map(CarMapper::toResponseDTO)
                .toList();
    }

    public CarResponseDTO buscarPorId(Long id, Long carroId){
        Car car = carroRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado!"));

        return carroMapper.toResponseDTO(car);
    }

    public CarResponseDTO criarCarro(Long id, CarRequestDTO carroDTO){
        Client client = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Car car = carroMapper.toEntidade(carroDTO);
        car.setClient(client);

        Car carSalvo = carroRepository.save(car);
        return carroMapper.toResponseDTO(carSalvo);
    }

    public void deletarCarro(Long id, Long carroId){
        Car car = carroRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() -> new RuntimeException("Carro não encontrado para este usuário"));

        carroRepository.delete(car);
    }

    public CarResponseDTO atualizarCarro(Long id, Long carroId, CarRequestDTO carroRequest){
        Car car = carroRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado para este usuário"));

        car.setPlaca(carroRequest.getPlaca());
        car.setMarca(carroRequest.getMarca());
        car.setModelo(carroRequest.getModelo());
        car.setCor(carroRequest.getCor());
        car.setAnoFabricacao(carroRequest.getAnoFabricacao());
        car.setTipoCombustivel(carroRequest.getTipoCombustivel());
        car.setTransmissao(carroRequest.getTransmissao());

        Car carAtualizado = carroRepository.save(car);
        return carroMapper.toResponseDTO(carAtualizado);
    }
}
