package se.backend.webapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.backend.webapi.models.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    // hitta baserat på namn
    List<Product> findByName(String name);

    // hitta baserat på prisintervall
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // hitta baserat på färg
    List<Product> findByColor(String color);
}
