package info.jab.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GreekGodsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreekGodsServiceApplication.class, args);
	}

}
