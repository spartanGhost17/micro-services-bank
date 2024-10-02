package com.eazybank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//use the following if for some reason you main class is not in root of module and you will have to scan all the necessary packages
/*@ComponentScans({ @ComponentScan("com.eazybank.accounts.controller")})//all beans
@EnableJpaRepositories("com.eazybank.accounts.repository")
@EntityScan("com.eazybank.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")//will get the current auditor name for this current service (maybe a table can be updated by multiple people)
@OpenAPIDefinition(info = @Info(
		title = "Account microservice REST API documentation",
		description = "EazyBank Accounts microservices REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "James Bond",
				email = "jamesbond@agent.com",
				url = "https://google.com"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://google.com"
		)

	),
	externalDocs = @ExternalDocumentation(
			description = "EazyBank Accounts microservice REST API Documentation",
			url = "https://google.com"
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
