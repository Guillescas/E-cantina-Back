package br.com.projeto.ecantina.dto.request;

import java.util.Optional;

import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestProductDto {
    
    private String name;
    private String price;
    private String description;
    private String type;

    private String restaurantId;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product convert(RestaurantRepository restaurantRepository) {
        Product product = new Product(getName(), getType(), getPrice());

        Optional<Restaurant> restaurantNewProduct = restaurantRepository.findById(Long.valueOf(getRestaurantId()));
        Restaurant restaurant = restaurantNewProduct.get();
        restaurant.getProducts().add(product);
        restaurantRepository.save(restaurant);
        
        return product;
    }
    
}
