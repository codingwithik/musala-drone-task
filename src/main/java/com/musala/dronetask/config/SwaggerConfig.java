package com.musala.dronetask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import static java.util.Arrays.asList;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI api() {

        return new OpenAPI()
                .info(new Info()
                        .version("v1")
                        .description("A Demo project for a drone application, with functionalities which "
                        		+ "include delivery of small items that are (urgently) needed in locations with difficult access.")
                        .title("Musala Drone Service"))
                .servers(
                        asList(
                                new Server()
                                        .url("http://localhost:8100")
                                        .description("LOCAL")
                        )
                );
    }
}
