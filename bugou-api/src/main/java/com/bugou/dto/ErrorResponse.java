package com.bugou.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(

        @Schema(example = "2026-06-10T14:30:00")
        LocalDateTime timestamp,

        @Schema(example = "404")
        int status,

        @Schema(example = "Error not found with id: 999")
        String message,

        @Schema(example = "[\"Title is required\"]")
        List<String> errors

) {
}