package com.ws101.longcop.dayda.EcommerceApi.Service;

import com.ws101.longcop.dayda.EcommerceApi.Model.Category;
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
    private Long currentId = 1L;

    public ProductService() {
        // Initialize formal Category objects to replace the raw text strings
        Category electronics = new Category(null, "Electronics", null);
        Category apparel = new Category(null, "Apparel", null);
        Category accessories = new Category(null, "Accessories", null);
        Category kitchen = new Category(null, "Kitchen", null);

        // Initialize with 10 sample products matching the updated constructor argument types
        productList.add(new Product(currentId++, "Mechanical Keyboard", "RGB Backlit Mechanical Keyboard", 89.99, 15, "https://example.com/kbd.jpg", electronics));
        productList.add(new Product(currentId++, "Wireless Mouse", "Ergonomic 2.4GHz wireless mouse", 29.99, 30, "https://example.com/mouse.jpg", electronics));
        productList.add(new Product(currentId++, "Gaming Monitor", "27-inch 144Hz IPS display", 249.99, 8, "https://example.com/monitor.jpg", electronics));
        productList.add(new Product(currentId++, "Running Shoes", "Lightweight breathable mesh sneakers", 65.00, 25, "https://example.com/shoes.jpg", apparel));
        productList.add(new Product(currentId++, "Cotton Hoodie", "Comfortable heavy-blend streetwear hoodie", 45.00, 40, "https://example.com/hoodie.jpg", apparel));
        productList.add(new Product(currentId++, "Leather Wallet", "Slim minimalist genuine leather wallet", 35.50, 50, "https://example.com/wallet.jpg", accessories));
        productList.add(new Product(currentId++, "Smart Watch", "Fitness tracker with heart rate monitor", 120.00, 12, "https://example.com/watch.jpg", electronics));
        productList.add(new Product(currentId++, "Desk Mat", "Extra large waterproof micro-weave desk pad", 19.99, 100, "https://example.com/mat.jpg", accessories));
        productList.add(new Product(currentId++, "Coffee Mug", "Ceramic temperature retention mug", 14.99, 60, "https://example.com/mug.jpg", kitchen));
        productList.add(new Product(currentId++, "Backpack", "Water-resistant travel laptop backpack", 55.00, 18, "https://example.com/backpack.jpg", accessories));
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
            if (partialProduct.getPrice() != null && partialProduct.getPrice() != 0) existing.setPrice(partialProduct.getPrice());
            if (partialProduct.getCategory() != null) existing.setCategory(partialProduct.getCategory());
            if (partialProduct.getStockQuantity() >= 0) existing.setStockQuantity(partialProduct.getStockQuantity());
            if (partialProduct.getImageUrl() != null) existing.setImageUrl(partialProduct.getImageUrl());
            if (partialProduct.getPrice() != null && partialProduct.getPrice() != 0.0) existing.setPrice(partialProduct.getPrice());
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
                    // Access the string name property inside our Category entity object safely
                    return product.getCategory() != null &&
                            product.getCategory().getName() != null &&
                            product.getCategory().getName().equalsIgnoreCase(filterValue);
                case "price":
                    return product.getPrice() <= Double.parseDouble(filterValue);
                default:
                    return false;
            }
        }).collect(Collectors.toList());
    }
}