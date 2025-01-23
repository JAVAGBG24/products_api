package se.backend.webapi.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @NotNull(message = "Title can not be null")
    @NotEmpty(message = "Title can not be empty")
    private String name;


    private String description;

    private String color;

    @Positive(message = "Price must be greater than 0")
    @Negative(message = "Price can not be negative number")
    private Double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    @NotNull(message = "Stock quantity is required")
    private Integer stockQuantity;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull(message = "Title can not be null") @NotEmpty(message = "Title can not be empty") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Title can not be null") @NotEmpty(message = "Title can not be empty") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public @NotNull(message = "Title can not be null") @NotEmpty(message = "Title can not be empty") String getColor() {
        return color;
    }

    public void setColor(@NotNull(message = "Title can not be null") @NotEmpty(message = "Title can not be empty") String color) {
        this.color = color;
    }

    public @Positive(message = "Price must be greater than 0") @Negative(message = "Price can not be negative number") Double getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Price must be greater than 0") @Negative(message = "Price can not be negative number") Double price) {
        this.price = price;
    }

    public @Min(value = 0, message = "Stock quantity cannot be negative") @NotNull(message = "Stock quantity is required") Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@Min(value = 0, message = "Stock quantity cannot be negative") @NotNull(message = "Stock quantity is required") Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
