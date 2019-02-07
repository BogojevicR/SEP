package ftn.NaucnaCentralaR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackageClasses = NaucnaCentralaApp.class)
@EnableJpaRepositories
public class NaucnaCentralaApp {
	public static void main(String... args) {
		SpringApplication.run(NaucnaCentralaApp.class, args);
	}

}
