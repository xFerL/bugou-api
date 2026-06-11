package com.bugou.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorKnowledgeResponse(

        @Schema(example = "1")
        Long id,

        @Schema(example = "JAVA-001")
        String code,

        @Schema(example = "NullPointerException")
        String title,

        @Schema(example = "JAVA")
        String category,

        @Schema(
                example = "Attempt to access a null object reference."
        )
        String explanation

) {
}