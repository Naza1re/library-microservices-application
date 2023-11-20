package com.example.libraryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.class, Builder.operationBuilder().getClass());
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Library-Controller-API").version("1.0.0"));
    }
}
