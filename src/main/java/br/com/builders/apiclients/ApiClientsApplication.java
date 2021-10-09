package br.com.builders.apiclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientsApplication.class, args);
	}

}
