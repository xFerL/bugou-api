package com.bugou.controller;

import com.bugou.dto.ErrorKnowledgeRequest;
import com.bugou.dto.ErrorKnowledgeResponse;
import com.bugou.entity.ErrorKnowledge;
import com.bugou.service.ErrorKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.bugou.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(
        name = "Error Knowledge",
        description = "Gerenciamento de erros de programação"
)
@RestController
@RequestMapping("/api/v1/errors")
public class ErrorKnowledgeController {

    private final ErrorKnowledgeService service;

    public ErrorKnowledgeController(ErrorKnowledgeService service) {
        this.service = service;
    }

    @Operation(
            summary = "Criar um novo erro",
            description = "Registra um novo erro na base de conhecimento"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Erro criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorKnowledgeResponse criar(
            @Valid @RequestBody ErrorKnowledgeRequest request) {

        ErrorKnowledge errorKnowledge = new ErrorKnowledge();

        errorKnowledge.setCode(request.code());
        errorKnowledge.setTitle(request.title());
        errorKnowledge.setCategory(request.category());
        errorKnowledge.setExplanation(request.explanation());

        ErrorKnowledge saved = service.salvar(errorKnowledge);

        return new ErrorKnowledgeResponse(
                saved.getId(),
                saved.getCode(),
                saved.getTitle(),
                saved.getCategory(),
                saved.getExplanation()
        );
    }

    @Operation(
            summary = "Listar todos os erros",
            description = "Retorna todos os erros cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso"
    )
    @GetMapping
    public List<ErrorKnowledgeResponse> buscarTodos() {

        return service.buscarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Operation(
            summary = "Buscar erro por ID",
            description = "Retorna um erro utilizando seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Erro encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Erro não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ErrorKnowledgeResponse> buscarPorId(
            @PathVariable Long id) {

        ErrorKnowledge errorKnowledge =
                service.buscarPorId(id);

        return ResponseEntity.ok(
                toResponse(errorKnowledge)
        );
    }

    @Operation(
            summary = "Buscar erro por título",
            description = "Retorna um erro utilizando seu título"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Erro encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Erro não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @GetMapping("/search")
    public ResponseEntity<ErrorKnowledgeResponse> buscarPorTitulo(
            @RequestParam String title) {

        ErrorKnowledge errorKnowledge =
                service.buscarPorTitulo(title);

        return ResponseEntity.ok(
                toResponse(errorKnowledge)
        );
    }

    private ErrorKnowledgeResponse toResponse(
            ErrorKnowledge errorKnowledge) {

        return new ErrorKnowledgeResponse(
                errorKnowledge.getId(),
                errorKnowledge.getCode(),
                errorKnowledge.getTitle(),
                errorKnowledge.getCategory(),
                errorKnowledge.getExplanation()
        );
    }
}