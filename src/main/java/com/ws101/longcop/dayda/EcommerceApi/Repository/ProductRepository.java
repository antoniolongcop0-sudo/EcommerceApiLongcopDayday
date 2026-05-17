package com.ws101.longcop.dayda.EcommerceApi.Repository;

import com.ws101.longcop.dayda.EcommerceApi.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}