package com.mrs.marketsurveys.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .select()
		        .apis(RequestHandlerSelectors
		                .basePackage("com.mrs.marketsurveys.controller"))
		        .paths(PathSelectors.any())
		        .build()
		        .securitySchemes(Arrays.asList(apiKey()))
		        .securityContexts(Arrays.asList(securityContext()))
		        .apiInfo(apiInfo());

	}

	@Bean
	public SecurityScheme apiKey() {
		return new ApiKey("apiKey", HttpHeaders.AUTHORIZATION, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
		        .securityReferences(defaultAuth())
		        .build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global",
		        "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays
		        .asList(new SecurityReference("apiKey", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
		        .clientId("test-app-client-id")
		        .clientSecret("test-app-client-secret")
		        .realm("test-app-realm")
		        .appName("test-app")
		        .scopeSeparator(",")
		        .additionalQueryStringParams(null)
		        .useBasicAuthenticationWithAccessCodeGrant(false)
		        .build();
	}

	/**
	 *
	 * @return ApiInf
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Market Research System API")
		        .description("")
		        .version("1.0.0")
		        .build();

	}

}
