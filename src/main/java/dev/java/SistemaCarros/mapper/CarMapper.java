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

        car.setId(carroDTO.getId());
        car.setPlaca(carroDTO.getPlaca());
        car.setModelo(carroDTO.getModelo());
        car.setMarca(carroDTO.getMarca());
        car.setCor(carroDTO.getCor());
        car.setAnoFabricacao(carroDTO.getAnoFabricacao());
        car.setTipoCombustivel(carroDTO.getTipoCombustivel());
        car.setTransmissao(carroDTO.getTransmissao());

        return car;
    }
}
