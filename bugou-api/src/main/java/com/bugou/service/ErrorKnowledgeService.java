package com.bugou.service;

import com.bugou.entity.ErrorKnowledge;
import com.bugou.repository.ErrorKnowledgeRepository;
import org.springframework.stereotype.Service;

@Service
public class ErrorKnowledgeService {

    private final ErrorKnowledgeRepository repository;

    public ErrorKnowledgeService(ErrorKnowledgeRepository repository) {
        this.repository = repository;
    }

    public ErrorKnowledge salvar(ErrorKnowledge errorKnowledge) {
        return repository.save(errorKnowledge);
    }
}
