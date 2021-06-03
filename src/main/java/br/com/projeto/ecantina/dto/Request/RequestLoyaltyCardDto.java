package br.com.projeto.ecantina.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestLoyaltyCardDto {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  
    private String validThru;
  
    private Integer totalOrder;
  
    private Integer ordersDone;
  
    private Long restaurantId;

    public Integer getOrdersDone() {
        return ordersDone;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public LocalDate getValidThru() {
        return LocalDate.parse(this.validThru, formatter);
    }

    public LoyaltyCard convert(RestaurantRepository restaurantRepository) {

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(getRestaurantId());
        if (restaurantFind.isPresent()) {

            return new LoyaltyCard(LocalDate.now(), getValidThru(), getTotalOrder(), restaurantFind.get());
        }

        throw new NullPointerException("Restaurante não foi encontrado");
    }
    
}
