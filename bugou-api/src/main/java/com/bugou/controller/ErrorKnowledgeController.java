package com.bugou.controller;

import com.bugou.dto.ErrorKnowledgeRequest;
import com.bugou.dto.ErrorKnowledgeResponse;
import com.bugou.entity.ErrorKnowledge;
import com.bugou.service.ErrorKnowledgeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Error Knowledge", description = "Gerenciamento de erros de programação")
@RestController
@RequestMapping("/api/v1/errors")
public class ErrorKnowledgeController {

    private final ErrorKnowledgeService service;

    public ErrorKnowledgeController(ErrorKnowledgeService service) {
        this.service = service;
    }
    @Operation(summary = "Criar um novo erro")
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
    @Operation(summary = "Listar todos os erros")
    @GetMapping
    public List<ErrorKnowledgeResponse> buscarTodos() {

        return service.buscarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Operation(summary = "Buscar erro por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ErrorKnowledgeResponse> buscarPorId(
            @PathVariable Long id) {

        ErrorKnowledge errorKnowledge =
                service.buscarPorId(id);

        return ResponseEntity.ok(
                toResponse(errorKnowledge)
        );
    }

    @Operation(summary = "Buscar erro por título")
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
