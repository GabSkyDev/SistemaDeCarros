package dev.java.SistemaCarros.controller;

import dev.java.SistemaCarros.dto.MechanicalRequestDTO;
import dev.java.SistemaCarros.dto.MechanicalResponseDTO;
import dev.java.SistemaCarros.service.MechanicalService;
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
@RequestMapping("/mechanical")
@Tag(name = "Mecânica", description = "Endpoints para gerenciamento das mecânicas.")
public class MechanicalController {
    private final MechanicalService mechanicalService;

    @Autowired
    public MechanicalController(MechanicalService mechanicalService){
        this.mechanicalService = mechanicalService;
    }

    // Método GET
    @Operation(
            summary = "Lista todas as mecânicas.",
            description = "Retorna uma lista com todos os clientes cadastrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de clientes retornada com sucesso!"
    )
    @GetMapping
    public ResponseEntity<List<MechanicalResponseDTO>> listarTodos(){
        List<MechanicalResponseDTO> mecanicas = mechanicalService.buscarTodasMecanicas();
        return new ResponseEntity<>(mecanicas, HttpStatus.OK);
    }

    // Método GET
    @Operation(
            summary = "Busca uma mecânica por ID.",
            description = "Retorna as informações de uma mecânica específica com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecânica encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Mecânica não encontrada!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MechanicalResponseDTO> buscarPorId(
            @Parameter(description = "ID da mecânica.", example = "1")
            @PathVariable Long id
    ){
        MechanicalResponseDTO mecanica = mechanicalService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }

    // Método POST
    @Operation(
            summary = "Cria uma mecânica.",
            description = "Realiza a criação de uma mecânica e retorna suas informações."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mecânica criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados incorretos ou incompletos)!")
    })
    @PostMapping
    public ResponseEntity<MechanicalResponseDTO> criarMecanica(@RequestBody MechanicalRequestDTO mecanica){
        MechanicalResponseDTO novaMecanica = mechanicalService.criarMecanica(mecanica);
        return new ResponseEntity<>(novaMecanica,HttpStatus.CREATED);
    }

    // Método DELETE
    @Operation(
            summary = "Deleta uma mecânica."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecânica deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Mecânica não encontrada!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMecanica(
            @Parameter(description = "ID da mecânica que será deletada", example = "1")
            @PathVariable Long id
    ){
        mechanicalService.deletarMecanicaPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Método PUT
    @Operation(
            summary = "Atualiza os dados de uma mecânica existente.",
            description = "Atualiza as informações da mecânica identificada pelo ID, com os novos dados fornecidos no corpo da requisição."
    )
    @PutMapping("/{id}")
    public ResponseEntity<MechanicalResponseDTO> atualizarMecanica(
            @Parameter(description = "ID da mecânica a ser atualizada", example = "1")
            @PathVariable Long id,

            @RequestBody MechanicalRequestDTO mecanicaRequest
    ){
        mechanicalService.atualizarMecanica(id, mecanicaRequest);
        MechanicalResponseDTO mecanica = mechanicalService.buscarPorId(id);
        return new ResponseEntity<>(mecanica, HttpStatus.OK);
    }



}
