package com.bugou.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Bugou API",
                version = "1.0",
                description = "API de catálogo de erros e explicações para desenvolvedores"
        )
)
public class OpenApiConfig {
}