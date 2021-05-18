package br.com.projeto.ecantina.dto.request;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestProductDto {
    
    @NotBlank(message = "{name.blank}")
    @Size(max = 50, message = "{name.size}")
    private String name;
    
    @NotNull(message = "{price.null}")
    @Positive(message = "{price.positive}")
    private String price;

    @Size(max = 300, message = "{description.size}")
    private String description;

    @NotBlank(message = "{type.blank}")
    private String type;

    @NotNull(message = "{id.null}")
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
        Optional<Restaurant> restaurant = restaurantRepository.findById(Long.valueOf(getRestaurantId()));
        
        if(restaurant.isPresent()) {
            Product product = new Product(getName(), getType(), getPrice());
            restaurant.get().getProducts().add(product);
            restaurantRepository.save(restaurant.get());
            
            return product;
        } else {
            throw new NullPointerException("Restaurante:Restaurante não encontrado");
        }
    }
    
}
