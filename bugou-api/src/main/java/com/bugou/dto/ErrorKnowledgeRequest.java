package com.bugou.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorKnowledgeRequest(

        @Schema(example = "JAVA-001")
        @NotBlank(message = "Code is required")
        String code,

        @Schema(example = "NullPointerException")
        @NotBlank(message = "Title is required")
        String title,

        @Schema(example = "JAVA")
        @NotBlank(message = "Category is required")
        String category,

        @Schema(
                example = "Attempt to access a null object reference."
        )
        @NotBlank(message = "Explanation is required")
        String explanation

) {
}