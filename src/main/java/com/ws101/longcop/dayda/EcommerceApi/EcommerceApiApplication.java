package com.ws101.longcop.dayda.EcommerceApi;

import com.ws101.longcop.dayda.EcommerceApi.Model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class EcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiApplication.class, args);
	}

	@Repository
	public static interface ProductRepository extends JpaRepository<Product, Long> {
		// Keep this completely body-less. No constructors, no custom functions!
	}
}
