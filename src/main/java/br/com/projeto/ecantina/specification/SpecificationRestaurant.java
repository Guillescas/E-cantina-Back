package br.com.projeto.ecantina.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;

public class SpecificationRestaurant {
    
    public static Specification<Restaurant> restaurantName(String nameRestaurant) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("name"), "%" + nameRestaurant + "%");
    }

    public static Specification<Restaurant> establishmentId(Establishment establishment) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("establishment"), establishment);
    }
}
