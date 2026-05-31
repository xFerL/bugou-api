package com.bugou.repository;

import com.bugou.entity.ErrorKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorKnowledgeRepository
    extends JpaRepository<ErrorKnowledge, Long> {
}
