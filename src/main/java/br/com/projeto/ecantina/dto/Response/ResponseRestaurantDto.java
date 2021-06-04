package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseRestaurantDto {

    private Long id;
    private String email;
    private String name;
    private Category category;
    private String urlImage;
    private BigDecimal averageRating;
    private List<Rating> ratings;

    public ResponseRestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.email = restaurant.getEmail();
        this.name = restaurant.getName();
        this.category = restaurant.getCategories();
        this.urlImage = restaurant.getUrlImage();
        if (restaurant.getRatings() != null) {
            this.ratings = restaurant.getRatings();
            this.averageRating = getAverageRating(this.ratings);
        }
    }

    public BigDecimal getAverageRating(List<Rating> ratings) {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Rating rating : ratings) {
           totalSum = totalSum.add(rating.getValue());
        }
        return totalSum.divide(new BigDecimal(ratings.size()));
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Page<ResponseRestaurantDto> convert(Page<Restaurant> users) {
        return users.map(ResponseRestaurantDto::new);
    }
}
