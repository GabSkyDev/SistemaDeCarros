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

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientRequestDTO> buscarTodosUsuarios(){
        List<Client> clients = clientRepository.findAll();
        return clients
                .stream()
                .map(clientMapper::toRequestDTO)
                .toList();
    }

    public ClientWithCarsDTO buscarUsuarioComCarro(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));
        return clientMapper.toClientWithCarsDTO(client);
    }

    public List<ClientWithCarsDTO> buscarTodosUsuariosComCarro(){
        List<Client> clients = clientRepository.findAll();
        return clients
                .stream()
                .map(clientMapper::toClientWithCarsDTO)
                .toList();

    }
    public ClientResponseDTO buscarPorId(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Usuário não encontrado!"));

        return clientMapper.toResponseDTO(client);
    }
    public ClientResponseDTO criarUsuario(ClientRequestDTO usuarioDTO){
        Client client = clientMapper.toEntity(usuarioDTO);

        client.getCarrosRegistrados().forEach(carro -> carro.setClient(client));

        Client clientSalvo = clientRepository.save(client);
        return clientMapper.toResponseDTO(clientSalvo);
    }
    public void deletarUsuarioPorId(Long id){
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        clientRepository.deleteById(id);
    }

    public ClientResponseDTO atualizarUsuario(Long id, ClientRequestDTO usuarioRequest){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        client.setNome(usuarioRequest.nome());
        client.setCpf(usuarioRequest.cpf());
        client.setEmail(usuarioRequest.email());
        client.setTelefone(usuarioRequest.telefone());

        client.getCarrosRegistrados().clear();

        List<Car> cars = usuarioRequest.carrosRegistrados().stream().map(carroDTO -> {
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

        Client clientAtualizado = clientRepository.save(client);

        return clientMapper.toResponseDTO(clientAtualizado);
    }
}
