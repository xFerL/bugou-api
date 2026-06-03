package com.bugou.dto;

public record ErrorKnowledgeResponse(
        Long id,
        String code,
        String title,
        String category,
        String explanation
) {
}