package br.com.builders.apiclients.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.builders.apiclients.model.User;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.builders.apiclients"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(User.class)
                .globalRequestParameters(
                		Arrays.asList(new RequestParameterBuilder()
                                .name("Authorization")
                                .description("Header para Token JWT")
                                .in("header")
                                .required(false)
                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                                .build()));
	}

}
