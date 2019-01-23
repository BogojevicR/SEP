package ftn.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//TODO: BANKA 1. ONINIT FUNKCIJU KOJA VUCE IZ BAZE NA OSNOVU HOSTA PODATKE O BANCI
@SpringBootApplication
@ComponentScan(basePackageClasses = Application.class)
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
