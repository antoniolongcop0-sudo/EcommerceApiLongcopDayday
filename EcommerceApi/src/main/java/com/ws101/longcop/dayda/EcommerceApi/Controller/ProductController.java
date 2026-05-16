package com.ws101.longcop.dayda.EcommerceApi.Controller;

import com.ws101.longcop.dayda.EcommerceApi.Model.Product;
import com.ws101.longcop.dayda.EcommerceApi.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller handling incoming HTTP requests for products.
 * Maps HTTP routes to the product service logic layer.
 * @author Antonio Longcop Jr. N.
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        try {
            List<Product> results = productService.filterProducts(filterType, filterValue);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid filter parameters.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        // Validation Checks
        if (product.getName() == null || product.getName().trim().length() < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name is required (min 2 characters).");
        }
        if (product.getPrice() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be a positive number.");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is required.");
        }
        if (product.getStockQuantity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock quantity cannot be negative.");
        }

        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProduct(@PathVariable Long id, @RequestBody Product product) {
        if (product.getPrice() <= 0 || product.getStockQuantity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input fields provided.");
        }
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProductPartially(@PathVariable Long id, @RequestBody Product product) {
        return productService.patchProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}