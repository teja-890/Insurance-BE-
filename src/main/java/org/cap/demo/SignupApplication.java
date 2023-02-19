package org.cap.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@ComponentScan("org.cap.demo.*")
@EntityScan("org.cap.demo.json")
@EnableJpaRepositories("org.cap.demo.dao")
@OpenAPIDefinition(info = @Info(title = "Policy API", version = "2.0", description = "Policy Information"))
@SecurityScheme(name = "policy", scheme="Bearer",type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SignupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignupApplication.class, args);
	}

}
