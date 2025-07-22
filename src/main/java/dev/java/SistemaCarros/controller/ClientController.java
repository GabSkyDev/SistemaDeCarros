package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.ClientWithCarsDTO;
import dev.java.SistemaCarros.dto.ClientRequestDTO;
import dev.java.SistemaCarros.dto.ClientResponseDTO;
import dev.java.SistemaCarros.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes.")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Método GET
    @Operation(
            summary = "Lista todos os clientes.",
            description = "Retorna uma lista com todos os clientes cadastrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de clientes retornada com sucesso!"
    )
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listarTodos(){
        List<ClientResponseDTO> ListaUsuarios = clientService.buscarTodosUsuarios();
        return new ResponseEntity<>(ListaUsuarios, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca um cliente por ID.",
            description = "Retorna as informações de um cliente específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> buscarPorId(
            @Parameter(description = "ID do cliente.", example = "1")
            @PathVariable Long id
    ){
        ClientResponseDTO usuario = clientService.buscarPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca um cliente com seus carros por ID.",
            description = "Retorna as informações de um cliente específico junto as informações de seus carros com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @GetMapping("/car/{id}")
    public ResponseEntity<ClientWithCarsDTO> buscarPorIdComCarro(
            @Parameter(description = "ID do cliente.", example = "1")
            @PathVariable Long id
    ){
        ClientWithCarsDTO usuario = clientService.buscarUsuarioComCarro(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca todos os clientes com seus carros.",
            description = "Retorna as informações de todos os clientes junto as informações de seus carros."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de clientes retornada com sucesso!"
    )
    @GetMapping("/cars")
    public ResponseEntity<List<ClientWithCarsDTO>> listarTodosComCarro(){
        List<ClientWithCarsDTO> usuarios = clientService.buscarTodosUsuariosComCarro();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Método POST
    @Operation(
            summary = "Cria um cliente.",
            description = "Realiza a criação de um cliente e retorna suas informações."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!")
    })
    @PostMapping
    public ResponseEntity<ClientResponseDTO> criarUsuario(@RequestBody ClientRequestDTO usuario){
        ClientResponseDTO novoUsuario = clientService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // Método DELETE
    @Operation(
            summary = "Deleta um cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do cliente que será deletado", example = "4")
            @PathVariable Long id
    ){
        clientService.deletarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Método PUT
    @Operation(
            summary = "Atualiza os dados de um cliente existente.",
            description = "Atualiza as informações do cliente identificado pelo ID, com os novos dados fornecidos no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> atualizarUsuario(
            @Parameter(description = "ID do cliente a ser atualizado", example = "2")
            @PathVariable Long id,

            @RequestBody ClientRequestDTO novoUsuario
    ){
         clientService.atualizarUsuario(id, novoUsuario);
         ClientResponseDTO usuario = clientService.buscarPorId(id);
         return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
