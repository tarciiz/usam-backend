package br.com.usam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class UsamApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsamApplication.class, args);
	}

}
