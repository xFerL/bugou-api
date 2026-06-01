package com.bugou.config;

import com.bugou.entity.ErrorKnowledge;
import com.bugou.service.ErrorKnowledgeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ErrorKnowledgeService service;

    public DataLoader(ErrorKnowledgeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        ErrorKnowledge error = new ErrorKnowledge();

        error.setCode("JAVA-001");
        error.setTitle("NullPointerException");
        error.setCategory("JAVA");
        error.setExplanation(
                "Tentativa de acessar um objeto nulo."
        );

        service.salvar(error);

        System.out.println("Erro salvo com sucesso!");
    }
}