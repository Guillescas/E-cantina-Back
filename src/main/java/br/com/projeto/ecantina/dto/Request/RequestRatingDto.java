package br.com.projeto.ecantina.dto.request;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.exceptions.InvalidRatingException;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.RatingRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestRatingDto {

    @Positive(message = "${value.positive}")
    private BigDecimal value;

    @Size(max = 255, message = "${description.size}")
    private String description;

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long clientId;

    public Long getClientId() {
        return clientId;
    }

    public String getDescription() {
        return description;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Rating convert(RatingRepository ratingRepository, ClientRepository clientRepository, RestaurantRepository restaurantRepository) {

        Optional<Rating> ratingFind = ratingRepository.findByClientAndRestaurant(getClientId(), getRestaurantId());
        Optional<Client> clientFind = clientRepository.findById(getClientId());
        Optional<Restaurant> restaurantFind = restaurantRepository.findById(getRestaurantId());

        if (clientFind.isPresent() && restaurantFind.isPresent()) {
            if (ratingFind.isPresent()) {
                throw new InvalidRatingException("Você já avaliou este restaurante");
            }
            
            Rating rating = new Rating(getValue(), getDescription());
            restaurantFind.get().getRatings().add(rating);
            clientFind.get().getRatings().add(rating);

            return rating;
        }

        throw new NullPointerException("Restaurante ou Cliente não encontrados");
    }
    
}
