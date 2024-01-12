package br.com.desafio.backend.vehiclecontrolapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vehicle Control API")
                        .version("1.0.0")
                        .description("API for managing car and motorcycle parking. Enables the registration and control of vehicle entry and exit, as well as the management of establishments."
                        ));
    }

}