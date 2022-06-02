package com.ubqt;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class UbqtApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbqtApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mp;
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("ubqt API")
				.version("1.0")
				.description("ubqt back end API")
				.license(new License().name("ubqt")
				));
	}

}
