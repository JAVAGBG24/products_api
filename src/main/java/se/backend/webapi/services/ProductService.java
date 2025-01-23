package se.backend.webapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.backend.webapi.models.Product;
import se.backend.webapi.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        // kolla name, price och stockQuantity
       validateProduct(product);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // hämta produkter baserat på namn
    public List<Product> getProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name can not be null or empty.");
        }

        List<Product> products = productRepository.findByName(name);
        if (products.isEmpty()) {
            throw new NoSuchElementException("No products with name " + name);
        }

        return products;
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Price values can not be negative.");
        }

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("minPrice can not be greater than maxPrice.");
        }

        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        if (products.isEmpty()) {
            throw new NoSuchElementException("No products found within price range " + minPrice + " - " + maxPrice);
        }

        return products;
    }

    public List<Product> getProductsByColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Product color can not be null or empty.");
        }

        List<Product> products = productRepository.findByColor(color);
        if(products.isEmpty()) {
            throw new NoSuchElementException("No products found with color " + color);
        }

        return products;
    }



    private void validateProduct(Product product) {
        if(product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name can not be empty or null.");
        }

        if(product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price can not be less than 0.");
        }

        if(product.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Product stockQuantity can not be less than 0.");
        }
    }
























}
