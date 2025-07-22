package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.*;
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
        List<CarResponseDTO> carros = client.getCarrosRegistrados()
                .stream()
                .map(carro -> new CarResponseDTO(
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
                client.getCpf(),
                client.getNome(),
                client.getEmail(),
                client.getTelefone(),
                carros
        );
    }

    public Client toEntity(ClientRequestDTO usuarioDTO){
        Client client = new Client();

        client.setId(usuarioDTO.id());
        client.setCpf(usuarioDTO.cpf());
        client.setNome(usuarioDTO.nome());
        client.setEmail(usuarioDTO.email());
        client.setTelefone(usuarioDTO.telefone());

        List<Car> cars = usuarioDTO.carrosRegistrados().stream().map(carroDTO ->{
            Car car = new Car();
            car.setPlaca(carroDTO.placa());
            car.setModelo(carroDTO.modelo());
            car.setMarca(carroDTO.marca());
            car.setAnoFabricacao(carroDTO.anoFabricacao());
            car.setCor(carroDTO.cor());
            car.setTipoCombustivel(carroDTO.tipoCombustivel());
            car.setTransmissao(carroDTO.transmissao());
            car.setClient(client);
            return car;
        }).toList();

        client.setCarrosRegistrados(cars);

        return client;
    }
}
