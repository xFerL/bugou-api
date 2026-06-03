package com.bugou.service;

import com.bugou.entity.ErrorKnowledge;
import com.bugou.exception.ResourceNotFoundException;
import com.bugou.repository.ErrorKnowledgeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ErrorKnowledgeService {

    private final ErrorKnowledgeRepository repository;

    public ErrorKnowledgeService(ErrorKnowledgeRepository repository) {
        this.repository = repository;
    }

    public ErrorKnowledge salvar(ErrorKnowledge errorKnowledge) {
        return repository.save(errorKnowledge);
    }

    public List<ErrorKnowledge> buscarTodos() {
        return repository.findAll();
    }

    public ErrorKnowledge buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Error not found with id: " + id
                        ));
    }

    public ErrorKnowledge buscarPorTitulo(String title) {

        return repository.findByTitle(title)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Error not found with title: " + title
                        ));
    }
}
