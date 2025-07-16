package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.CarRequestDTO;
import dev.java.SistemaCarros.dto.ClientWithCarsDTO;
import dev.java.SistemaCarros.dto.ClientRequestDTO;
import dev.java.SistemaCarros.dto.ClientResponseDTO;
import dev.java.SistemaCarros.model.Car;
import dev.java.SistemaCarros.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {
    public ClientResponseDTO toResponseDTO(Client client){
        return new ClientResponseDTO(
                client.getCpf(),
                client.getNome(),
                client.getEmail(),
                client.getTelefone()
        );
    }

    public ClientRequestDTO toRequestDTO(Client client){
        return new ClientRequestDTO(
                client.getId(),
                client.getCpf(),
                client.getNome(),
                client.getEmail(),
                client.getTelefone()
        );
    }

    public ClientWithCarsDTO toClientWithCarsDTO(Client client){
        List<CarRequestDTO> carros = client.getCarrosRegistrados()
                .stream()
                .map(carro -> new CarRequestDTO(
                        carro.getId(),
                        carro.getPlaca(),
                        carro.getModelo(),
                        carro.getMarca(),
                        carro.getAnoFabricacao(),
                        carro.getCor(),
                        carro.getTipoCombustivel(),
                        carro.getTransmissao(),
                        client.getCpf()
                ))
                .toList();

        return new ClientWithCarsDTO(
                client.getId(),
                client.getCpf(),
                client.getNome(),
                client.getEmail(),
                client.getTelefone(),
                carros
        );
    }

    public Client toEntity(ClientRequestDTO usuarioDTO){
        Client client = new Client();

        client.setId(usuarioDTO.getId());
        client.setCpf(usuarioDTO.getCpf());
        client.setNome(usuarioDTO.getNome());
        client.setEmail(usuarioDTO.getEmail());
        client.setTelefone(usuarioDTO.getTelefone());

        List<Car> cars = usuarioDTO.getCarrosRegistrados().stream().map(carroDTO ->{
            Car car = new Car();
            car.setPlaca(carroDTO.getPlaca());
            car.setModelo(carroDTO.getModelo());
            car.setMarca(carroDTO.getMarca());
            car.setAnoFabricacao(carroDTO.getAnoFabricacao());
            car.setCor(carroDTO.getCor());
            car.setTipoCombustivel(carroDTO.getTipoCombustivel());
            car.setTransmissao(carroDTO.getTransmissao());
            car.setClient(client);
            return car;
        }).toList();

        client.setCarrosRegistrados(cars);

        return client;
    }
}
