package com.csn.commentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Comment Service API Documentation",
				description = "CSN comment service API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Sinan Cakal",
						email = "sinan.cakal@protonmail.com",
						url = "Not deployed yet"
				),
				license = @License(
						name = "Haven't licensed yet"	,
						url = "Not deployed yet"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Not external documentation"
		))
public class CommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}
