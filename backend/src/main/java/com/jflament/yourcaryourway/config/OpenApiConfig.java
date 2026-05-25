package com.jflament.yourcaryourway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Your Car Your Way - PoC API")
                        .version("1.0")
                        .description("Documentation OpenAPI de la preuve de concept du chat de support client")
                        .contact(new Contact()
                                .name("Jocelyn Flament")
                                .email("jocelyn@ycyw.test")));
    }
}