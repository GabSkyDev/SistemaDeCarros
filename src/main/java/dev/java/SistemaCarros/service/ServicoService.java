package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.ServicoRequestDTO;
import dev.java.SistemaCarros.dto.ServicoResponseDTO;
import dev.java.SistemaCarros.mapper.ServicoMapper;
import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Mecanica;
import dev.java.SistemaCarros.model.Servico;
import dev.java.SistemaCarros.repository.CarroRepository;
import dev.java.SistemaCarros.repository.MecanicaRepository;
import dev.java.SistemaCarros.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final MecanicaRepository mecanicaRepository;
    private final CarroRepository carroRepository;
    private ServicoMapper servicoMapper;
    @Autowired
    public ServicoService(ServicoRepository servicoRepository, MecanicaRepository mecanicaRepository, CarroRepository carroRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.mecanicaRepository = mecanicaRepository;
        this.carroRepository = carroRepository;
        this.servicoMapper = servicoMapper;
    }

    public List<ServicoResponseDTO> listarServicos() {
        return servicoRepository.findAll().stream()
                .map(ServicoMapper::toResponseDTO)
                .toList();
    }

    public ServicoResponseDTO buscarPorId(Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return servicoMapper.toResponseDTO(servico);
    }

    public ServicoResponseDTO criarServico(ServicoRequestDTO servicoDTO) {
        Mecanica mecanica = mecanicaRepository.findById(servicoDTO.getMecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Carro carro = null;
        if (servicoDTO.getCarroId() != null) {
            carro = carroRepository.findById(servicoDTO.getCarroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        Servico servico = new Servico();
        servico.setDescricao(servicoDTO.getDescricao());
        servico.setDataServico(servicoDTO.getDataServico());
        servico.setValorPago(servicoDTO.getValorPago());
        servico.setMecanica(mecanica);
        servico.setCarro(carro);
        servicoRepository.save(servico);

        return servicoMapper.toResponseDTO(servico);
    }

    public void deletarServico(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        servicoRepository.deleteById(id);
    }


    public ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO dto) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Mecanica mecanica = mecanicaRepository.findById(dto.getMecanicaId())
                .orElseThrow(() -> new RuntimeException("Mecânica não encontrada"));

        Carro carro = null;
        if (dto.getCarroId() != null) {
            carro = carroRepository.findById(dto.getCarroId())
                    .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        }

        servico.setDescricao(dto.getDescricao());
        servico.setDataServico(dto.getDataServico());
        servico.setValorPago(dto.getValorPago());
        servico.setMecanica(mecanica);
        servico.setCarro(carro);

        return servicoMapper.toResponseDTO(servicoRepository.save(servico));
    }

}
