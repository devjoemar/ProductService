package com.devjoemar.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner (CategoryRepository repository) {
//		return args -> {
//			var categoryPhone = Category.builder()
//							.name("Phones")
//							.description("Apple IPHONE Sereies")
//							.build();
//
//
//			repository.insert(categoryPhone);
//
//
//			var categoryLaptop = Category.builder()
//					.name("Laptop MAC")
//					.description("Apple Laptop Sereies")
//					.build();
//
//			repository.insert(categoryLaptop);
//		};
//	}
}
