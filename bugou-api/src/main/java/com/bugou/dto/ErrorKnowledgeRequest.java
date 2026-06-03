package com.bugou.dto;

import jakarta.validation.constraints.NotBlank;

public record ErrorKnowledgeRequest(

        @NotBlank(message = "Code is required")
        String code,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Category is required")
        String category,

        @NotBlank(message = "Explanation is required")
        String explanation

) {
}