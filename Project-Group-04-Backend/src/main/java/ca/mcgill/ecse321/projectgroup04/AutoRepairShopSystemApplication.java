package ca.mcgill.ecse321.projectgroup04;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class AutoRepairShopSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoRepairShopSystemApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

}
