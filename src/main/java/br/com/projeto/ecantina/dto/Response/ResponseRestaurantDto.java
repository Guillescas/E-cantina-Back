package br.com.projeto.ecantina.dto.response;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Restaurant;

public class ResponseRestaurantDto {
    
    private Long id;
    private String email;
    private String name;

    public ResponseRestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.email = restaurant.getEmail();
        this.name = restaurant.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
