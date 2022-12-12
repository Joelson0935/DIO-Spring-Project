package com.casa.dio.configuration.swagger;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class Swagger {

	private Contact contato() {
		return new Contact("Joelson Cerqueira", "https://github.com/Joelson0935/DIO-Spring-Project", "joelsonluiz2010@gmail.com");
	}

	private ApiInfoBuilder informacoesApi() {
		ApiInfoBuilder apiBuilder = new ApiInfoBuilder();
		apiBuilder.title("Cadastro de alunos e suas avaliações físicas");
		apiBuilder.description("Api Rest de cadastro de alunos para entregar como desafio no Bootcamp da DIO.");
		apiBuilder.version("1.0");
		apiBuilder.termsOfServiceUrl("Open Source");
		apiBuilder.license("");
		apiBuilder.licenseUrl("");
		apiBuilder.contact(this.contato());
		return apiBuilder;
	}
	
	@Bean
	Docket detalheApi () {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.casa.dio.controllers"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build())
		.consumes(new HashSet<String>(Arrays.asList("application/json")))
		.produces(new HashSet<String>(Arrays.asList("application/json")));
		return docket;
	}

	
}
