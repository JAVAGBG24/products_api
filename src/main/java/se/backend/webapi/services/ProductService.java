package se.backend.webapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.backend.webapi.models.Product;
import se.backend.webapi.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        // kolla name, price och stockQuantity
       validateProduct(product);

        return productRepository.save(product);
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
