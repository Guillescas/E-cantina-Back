package br.com.projeto.ecantina.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.models.Restaurant;

public class SpecificationLoyaltyCard {

    public static Specification<LoyaltyCard> restaurant(Restaurant restaurant) {
        return (root, criteriaQuery, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("restaurant"), restaurant);
    }

    public static Specification<LoyaltyCard> client(Client client) {
        return (root, criteriaQuery, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("client"), client);
    }
}
