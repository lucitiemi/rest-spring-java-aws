package br.com.luciana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("RESTful API with Java 21 ans Spring Boot 3")
					.version("v1")
					.description("Some description about your API")
					.termsOfService("https://luciana.com.br/api")
					.license(new License()
							.name("Apache 2.0")
							.url("https://luciana.com.br/api"))
					);
	}

}
