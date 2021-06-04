package br.com.projeto.ecantina.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Order;

public class SpecificationOrder {
    
    public static Specification<Order> client(Client client) {
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("client"), client);
    }

    
}
