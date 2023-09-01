package com.microservice.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@OpenAPIDefinition(
		info=@Info(
				title="Department ServiceREST APIs",
				description = "Department Service REST APIS documentation",
				version="v1.0",
				contact=@Contact(
						name="Subhrajeet Pandab",
						email="subh123@gmail.com"
				),
				license = @License(
						name="Apache2.0"
				)
		),
		externalDocs=@ExternalDocumentation(
				description="DEPARTMENT-SERVICE Doc"
)
)
@SpringBootApplication

public class DepartmentServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
