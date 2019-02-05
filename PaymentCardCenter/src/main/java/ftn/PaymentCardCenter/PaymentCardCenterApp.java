package ftn.PaymentCardCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackageClasses = PaymentCardCenterApp.class)
@EnableJpaRepositories
public class PaymentCardCenterApp {

	public static void main(String[] args) {
		SpringApplication.run(PaymentCardCenterApp.class, args);

	}

}
