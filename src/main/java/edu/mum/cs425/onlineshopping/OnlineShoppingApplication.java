package edu.mum.cs425.onlineshopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.mum.cs425.onlineshopping.service.ProductService;
@SpringBootApplication
public class OnlineShoppingApplication {
	@Autowired
	ProductService productService;
	// Spring Boot 5.0 Requirement
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();   // BCrypt
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
	}
}
