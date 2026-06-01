package com.bugou.controller;

import com.bugou.entity.ErrorKnowledge;
import com.bugou.service.ErrorKnowledgeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}
