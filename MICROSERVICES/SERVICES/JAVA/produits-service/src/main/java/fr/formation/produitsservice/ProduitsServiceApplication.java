package fr.formation.produitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProduitsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProduitsServiceApplication.class, args);
	}
}
