package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    public List<Carro> buscarTodosCarros(){
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarPorId(Long id){
        return carroRepository.findById(id);
    }

    public Optional<Carro> buscarPorPlaca(String placa){
        return carroRepository.findByPlaca(placa);
    }

    public List<Carro> buscarPorModelo(String modelo){
        return carroRepository.findByModelo(modelo);
    }

    public List<Carro> buscarPorMarca(String marca){
        return carroRepository.findByMarca(marca);
    }

    public List<Carro> buscarPorAnoFabricacao(Integer anoFabricacao){
        return carroRepository.findByAnoFabricacao(anoFabricacao);
    }

    public List<Carro> buscarPorCor(String cor){
        return carroRepository.findByCor(cor);
    }

    public List<Carro> buscarPorTipoCombustivel(String tipoCombustivel){
        return carroRepository.findByTipoCombustivel(tipoCombustivel);
    }

    public List<Carro> buscarPorTransmissao(String transmissao){
        return carroRepository.findByTransmissao(transmissao);
    }

    public List<Carro> buscarCarroPorUsuarioId(Long usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
