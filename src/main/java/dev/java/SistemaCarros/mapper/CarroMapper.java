package dev.java.SistemaCarros.mapper;

import dev.java.SistemaCarros.dto.CarroRequestDTO;
import dev.java.SistemaCarros.dto.CarroResponseDTO;
import dev.java.SistemaCarros.model.Carro;

public class CarroMapper {
    public static CarroResponseDTO toResponseDTO(Carro carro){
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

    public static Carro toEntidade(CarroRequestDTO carroDTO){
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
