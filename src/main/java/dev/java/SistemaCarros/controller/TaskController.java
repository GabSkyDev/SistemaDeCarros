package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.TaskRequestDTO;
import dev.java.SistemaCarros.dto.TaskResponseDTO;
import dev.java.SistemaCarros.service.TaskService;
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
@RequestMapping("/task")
@Tag(name = "Serviços", description = "Endpoints para gerenciamento de serviços realizados.")
public class TaskController {
    private final TaskService servicoService;

    @Autowired
    public TaskController(TaskService servicoService) {
        this.servicoService = servicoService;
    }

    // Método GET
    @Operation(
            summary = "Lista todos os serviços.",
            description = "Retorna uma lista com todos os serviços realizados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de clientes retornada com sucesso!"
    )
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarServicos() {
        List<TaskResponseDTO> servicos = servicoService.listarServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca um serviço por ID.",
            description = "Retorna as informações de um serviço específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> buscarPorId(
            @Parameter(description = "ID do serviço.", example = "1")
            @PathVariable Long id
    ){
        TaskResponseDTO servico = servicoService.buscarPorId(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    // Método POST
    @Operation(
            summary = "Cadastra um serviço.",
            description = "Realiza o cadastro de um serviço e retorna suas informações."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!")
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criarServico(@RequestBody TaskRequestDTO dto) {
        TaskResponseDTO novoServico = servicoService.criarServico(dto);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    // Método PUT
    @Operation(
            summary = "Atualiza os dados de um serviço existente.",
            description = "Atualiza as informações do serviço identificado pelo ID, com os novos dados fornecidos no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> atualizarServico(
            @Parameter(description = "ID do serviço a ser atualizado", example = "6")
            @PathVariable Long id,

            @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO servicoAtualizado = servicoService.atualizarServico(id, dto);
        return new ResponseEntity<>(servicoAtualizado, HttpStatus.OK);
    }

    // Método DELETE
    @Operation(
            summary = "Deleta um serviço."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Servico não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(
            @Parameter(description = "ID do serviço que será deletado", example = "6")
            @PathVariable Long id) {
        servicoService.deletarServico(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
