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

    public Carro buscarPorId(Long id){
        return carroRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Mecânica não encontrada!"));
    }

    public Carro criarCarro(Carro carro){
        return carroRepository.save(carro);
    }

    public void deletarCarro(Long id){
        carroRepository.deleteById(id);
    }

    public Carro atualizarCarro(Long id, Carro carroRequest){
        Carro carro = buscarPorId(id);

        carro.setPlaca(carroRequest.getPlaca());
        carro.setMarca(carroRequest.getMarca());
        carro.setModelo(carroRequest.getModelo());
        carro.setCor(carroRequest.getCor());
        carro.setAnoFabricacao(carroRequest.getAnoFabricacao());
        carro.setTipoCombustivel(carroRequest.getTipoCombustivel());
        carro.setTransmissao(carroRequest.getTransmissao());

        return carroRepository.save(carro);
    }

}
