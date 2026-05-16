package com.ws101.longcop.dayda.EcommerceApi.Service;

import com.ws101.longcop.dayda.EcommerceApi.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for product-related operations.
 * Provides business logic for filtering, searching, and managing products in-memory.
 * @author Antonio Longcop Jr. N.
 */
@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private Long currentId = 1L; // Changed to match your wrapper type Object

    public ProductService() {
        // Initialize with 10 sample products as required
        productList.add(new Product(currentId++, "Mechanical Keyboard", "RGB Backlit Mechanical Keyboard", 89.99, "Electronics", 15, "https://example.com/kbd.jpg"));
        productList.add(new Product(currentId++, "Wireless Mouse", "Ergonomic 2.4GHz wireless mouse", 29.99, "Electronics", 30, "https://example.com/mouse.jpg"));
        productList.add(new Product(currentId++, "Gaming Monitor", "27-inch 144Hz IPS display", 249.99, "Electronics", 8, "https://example.com/monitor.jpg"));
        productList.add(new Product(currentId++, "Running Shoes", "Lightweight breathable mesh sneakers", 65.00, "Apparel", 25, "https://example.com/shoes.jpg"));
        productList.add(new Product(currentId++, "Cotton Hoodie", "Comfortable heavy-blend streetwear hoodie", 45.00, "Apparel", 40, "https://example.com/hoodie.jpg"));
        productList.add(new Product(currentId++, "Leather Wallet", "Slim minimalist genuine leather wallet", 35.50, "Accessories", 50, "https://example.com/wallet.jpg"));
        productList.add(new Product(currentId++, "Smart Watch", "Fitness tracker with heart rate monitor", 120.00, "Electronics", 12, "https://example.com/watch.jpg"));
        productList.add(new Product(currentId++, "Desk Mat", "Extra large waterproof micro-weave desk pad", 19.99, "Accessories", 100, "https://example.com/mat.jpg"));
        productList.add(new Product(currentId++, "Coffee Mug", "Ceramic temperature retention mug", 14.99, "Kitchen", 60, "https://example.com/mug.jpg"));
        productList.add(new Product(currentId++, "Backpack", "Water-resistant travel laptop backpack", 55.00, "Accessories", 18, "https://example.com/backpack.jpg"));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Optional<Product> getProductById(Long id) {
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(currentId++);
        productList.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return getProductById(id).map(existing -> {
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setCategory(updatedProduct.getCategory());
            existing.setStockQuantity(updatedProduct.getStockQuantity());
            existing.setImageUrl(updatedProduct.getImageUrl());
            return existing;
        });
    }

    public Optional<Product> patchProduct(Long id, Product partialProduct) {
        return getProductById(id).map(existing -> {
            if (partialProduct.getName() != null) existing.setName(partialProduct.getName());
            if (partialProduct.getDescription() != null) existing.setDescription(partialProduct.getDescription());
            if (partialProduct.getPrice() != 0) existing.setPrice(partialProduct.getPrice());
            if (partialProduct.getCategory() != null) existing.setCategory(partialProduct.getCategory());
            if (partialProduct.getStockQuantity() >= 0) existing.setStockQuantity(partialProduct.getStockQuantity());
            if (partialProduct.getImageUrl() != null) existing.setImageUrl(partialProduct.getImageUrl());
            return existing;
        });
    }

    public boolean deleteProduct(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> filterProducts(String filterType, String filterValue) {
        return productList.stream().filter(product -> {
            switch (filterType.toLowerCase()) {
                case "name":
                    return product.getName().toLowerCase().contains(filterValue.toLowerCase());
                case "category":
                    return product.getCategory().toLowerCase().equalsIgnoreCase(filterValue);
                case "price":
                    return product.getPrice() <= Double.parseDouble(filterValue);
                default:
                    return false;
            }
        }).collect(Collectors.toList());
    }
}