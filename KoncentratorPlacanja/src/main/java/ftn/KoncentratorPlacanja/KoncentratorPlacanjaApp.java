package ftn.KoncentratorPlacanja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackageClasses = KoncentratorPlacanjaApp.class)
@EnableJpaRepositories
public class KoncentratorPlacanjaApp {
	
	public static void main(String[] args) {
		SpringApplication.run(KoncentratorPlacanjaApp.class, args);

	}

}
