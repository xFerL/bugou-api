package com.bugou.repository;

import com.bugou.entity.ErrorKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ErrorKnowledgeRepository
    extends JpaRepository<ErrorKnowledge, Long> {

    Optional<ErrorKnowledge> findByTitle(String title);
}
