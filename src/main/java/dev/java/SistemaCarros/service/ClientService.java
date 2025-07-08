package dev.java.SistemaCarros.service;

import dev.java.SistemaCarros.dto.ClientWithCarsDTO;
import dev.java.SistemaCarros.dto.ClientRequestDTO;
import dev.java.SistemaCarros.dto.ClientResponseDTO;
import dev.java.SistemaCarros.mapper.ClientMapper;
import dev.java.SistemaCarros.model.Car;
import dev.java.SistemaCarros.model.Client;
import dev.java.SistemaCarros.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository usuarioRepository;
    private ClientMapper usuarioMapper;

    @Autowired
    public ClientService(ClientRepository usuarioRepository, ClientMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<ClientRequestDTO> buscarTodosUsuarios(){
        List<Client> clients = usuarioRepository.findAll();
        return clients
                .stream()
                .map(ClientMapper::toRequestDTO)
                .toList();
    }

    public ClientWithCarsDTO buscarUsuarioComCarro(Long id){
        Client client = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));
        return usuarioMapper.toUsuarioComCarrosDTO(client);
    }

    public List<ClientWithCarsDTO> buscarTodosUsuariosComCarro(){
        List<Client> clients = usuarioRepository.findAll();
        return clients
                .stream()
                .map(ClientMapper::toUsuarioComCarrosDTO)
                .toList();

    }
    public ClientResponseDTO buscarPorId(Long id){
        Client client = usuarioRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));

        return usuarioMapper.toResponseDTO(client);
    }
    public ClientResponseDTO criarUsuario(ClientRequestDTO usuarioDTO){
        Client client = usuarioMapper.toEntidade(usuarioDTO);

        client.getCarrosRegistrados().forEach(carro -> carro.setClient(client));

        Client clientSalvo = usuarioRepository.save(client);
        return usuarioMapper.toResponseDTO(clientSalvo);
    }
    public void deletarUsuarioPorId(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public ClientResponseDTO atualizarUsuario(Long id, ClientRequestDTO usuarioRequest){
        Client client = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        client.setNome(usuarioRequest.getNome());
        client.setCpf(usuarioRequest.getCpf());
        client.setEmail(usuarioRequest.getEmail());
        client.setTelefone(usuarioRequest.getTelefone());

        client.getCarrosRegistrados().clear();

        List<Car> cars = usuarioRequest.getCarrosRegistrados().stream().map(carroDTO -> {
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

        client.getCarrosRegistrados().addAll(cars);

        Client clientAtualizado = usuarioRepository.save(client);

        return usuarioMapper.toResponseDTO(clientAtualizado);
    }
}
