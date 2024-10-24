package com.bandeira.sistema_aposentadoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaAposentadoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAposentadoriaApplication.class, args);
	}

}
