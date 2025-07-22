package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.CarRequestDTO;
import dev.java.SistemaCarros.dto.CarResponseDTO;
import dev.java.SistemaCarros.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarResponseDTO toResponseDTO(Car car){
        if (car.getClient() == null) {
            throw new RuntimeException("Carro sem usuário vinculado.");
        }

        return new CarResponseDTO(
                car.getPlaca(),
                car.getModelo(),
                car.getMarca(),
                car.getAnoFabricacao(),
                car.getCor(),
                car.getTipoCombustivel(),
                car.getTransmissao(),
                car.getClient().getCpf()
        );
    }

    public Car toEntity(CarRequestDTO carroDTO){
        Car car = new Car();

        car.setId(carroDTO.id());
        car.setPlaca(carroDTO.placa());
        car.setModelo(carroDTO.modelo());
        car.setMarca(carroDTO.marca());
        car.setCor(carroDTO.cor());
        car.setAnoFabricacao(carroDTO.anoFabricacao());
        car.setTipoCombustivel(carroDTO.tipoCombustivel());
        car.setTransmissao(carroDTO.transmissao());

        return car;
    }
}
