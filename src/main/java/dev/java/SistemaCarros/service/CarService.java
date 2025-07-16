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

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private CarMapper carMapper;
    @Autowired
    public CarService(CarRepository carRepository, ClientRepository clientRepository, CarMapper carMapper){
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.carMapper = carMapper;
    }

    public List<CarResponseDTO> buscarTodosCarrosPorUsuario(Long usuarioId){
        List<Car> cars = carRepository.findByClientId(usuarioId);

        return cars
                .stream()
                .map(carMapper::toResponseDTO)
                .toList();
    }

    public CarResponseDTO buscarPorId(Long id, Long carroId){
        Car car = carRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado!"));

        return carMapper.toResponseDTO(car);
    }

    public CarResponseDTO criarCarro(Long id, CarRequestDTO carroDTO){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Car car = carMapper.toEntity(carroDTO);
        car.setClient(client);

        Car carSalvo = carRepository.save(car);
        return carMapper.toResponseDTO(carSalvo);
    }

    public void deletarCarro(Long id, Long carroId){
        Car car = carRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() -> new RuntimeException("Carro não encontrado para este usuário"));

        carRepository.delete(car);
    }

    public CarResponseDTO atualizarCarro(Long id, Long carroId, CarRequestDTO carroRequest){
        Car car = carRepository.findById(carroId)
                .filter(c -> c.getClient().getId().equals(id))
                .orElseThrow(() ->  new RuntimeException("Carro não encontrado para este usuário"));

        car.setPlaca(carroRequest.getPlaca());
        car.setMarca(carroRequest.getMarca());
        car.setModelo(carroRequest.getModelo());
        car.setCor(carroRequest.getCor());
        car.setAnoFabricacao(carroRequest.getAnoFabricacao());
        car.setTipoCombustivel(carroRequest.getTipoCombustivel());
        car.setTransmissao(carroRequest.getTransmissao());

        Car carAtualizado = carRepository.save(car);
        return carMapper.toResponseDTO(carAtualizado);
    }
}
