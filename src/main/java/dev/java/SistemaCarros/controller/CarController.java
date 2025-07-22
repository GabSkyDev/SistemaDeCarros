package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.CarRequestDTO;
import dev.java.SistemaCarros.dto.CarResponseDTO;
import dev.java.SistemaCarros.service.CarService;
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
@RequestMapping("/client/{usuarioId}/car")
@Tag(name = "Carro", description = "Endpoints para gerenciamento do carro de clientes.")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Método GET
    @Operation(
            summary = "Lista todos os carros de um cliente específico.",
            description = "Retorna uma lista com todos os carros cadastrados no ID do cliente."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de carros retornada com sucesso!"
    )
    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> listarTodos(
            @Parameter(description = "ID do cliente dono do carro.")
            @PathVariable Long usuarioId){
        List<CarResponseDTO> carros = carService.buscarTodosCarrosPorUsuario(usuarioId);
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca um carro por ID.",
            description = "Retorna as informações de um carro específico cadastrado no ID do cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado!")
    })
    @GetMapping("/{carroId}")
    public ResponseEntity<CarResponseDTO>  buscarPorId(
            @Parameter(description = "ID do cliente dono do carro.", example = "1")
            @PathVariable Long usuarioId,

            @Parameter(description = "ID do carro.", example = "1")
            @PathVariable Long carroId
    ){
        CarResponseDTO carro = carService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }

    // Método POST
    @Operation(
            summary = "Cria um carro para um cliente.",
            description = "Cria e associa um carro a um cliente existente com base no ID do cliente e nos dados fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @PostMapping
    public ResponseEntity<CarResponseDTO> criarCarro(
            @Parameter(description = "ID do usuário que receberá o carro", example = "1")
            @PathVariable Long usuarioId,

            @RequestBody CarRequestDTO carroRequest
    ){
        CarResponseDTO novoCarro = carService.criarCarro(usuarioId, carroRequest);
        return new ResponseEntity<>(novoCarro, HttpStatus.CREATED);
    }

    // Método DELETE
    @Operation(
            summary = "Deleta um carro de um cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado!")
    })
    @DeleteMapping("/{carroId}")
    public ResponseEntity<Void> deletarCarro(
            @Parameter(description = "ID do cliente que deletará o carro", example = "4")
            @PathVariable Long usuarioId,

            @Parameter(description = "ID do carro que será deletado", example = "10")
            @PathVariable Long carroId
    ){
        carService.deletarCarro(usuarioId, carroId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Método PUT
    @Operation(
            summary = "Atualiza os dados de um carro existente",
            description = "Atualiza as informações do carro identificado pelo ID, com os novos dados fornecidos no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado!")
    })
    @PutMapping("/{carroId}")
    public ResponseEntity<CarResponseDTO> atualizarCarro(
            @Parameter(description = "ID do cliente a ter o carro atualizado", example = "3")
            @PathVariable Long usuarioId,

            @Parameter(description = "ID do carro que será atualizado", example = "1")
            @PathVariable Long carroId,

            @RequestBody CarRequestDTO carroRequest
    ){
        carService.atualizarCarro(usuarioId, carroId, carroRequest);
        CarResponseDTO carro = carService.buscarPorId(usuarioId, carroId);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }
}
