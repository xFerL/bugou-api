package com.bugou.controller;

import com.bugou.entity.ErrorKnowledge;
import com.bugou.service.ErrorKnowledgeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/errors")
public class ErrorKnowledgeController {

    private final ErrorKnowledgeService service;

    public ErrorKnowledgeController(ErrorKnowledgeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorKnowledge criar(@RequestBody ErrorKnowledge errorKnowledge) {
        return service.salvar(errorKnowledge);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ErrorKnowledge> buscarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorKnowledge> buscarPorId(
            @PathVariable Long id) {

        Optional<ErrorKnowledge> errorKnowledge =
                service.buscarPorId(id);

        if (errorKnowledge.isPresent()) {
            return ResponseEntity.ok(errorKnowledge.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ErrorKnowledge> buscarPorTitulo(
            @RequestParam String title) {

        Optional<ErrorKnowledge> errorKnowledge =
                service.buscarPorTitulo(title);

        if (errorKnowledge.isPresent()) {
            return ResponseEntity.ok(errorKnowledge.get());
        }

        return ResponseEntity.notFound().build();
    }
}
