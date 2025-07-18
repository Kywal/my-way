package br.ufrn.myway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.ufrn.myway")
public class MywayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MywayApplication.class, args);
	}



}
