package br.com.projeto.ecantina.dto.response;

import java.time.LocalDate;

import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseLoyaltyCardDto {

    private Long id;

    private Boolean valid;

    private LocalDate dateCreated;

    private LocalDate validThru;

    private Integer totalOrder;

    private Integer ordersDone;

    private Restaurant restaurant;
    
    public ResponseLoyaltyCardDto(LoyaltyCard loyaltyCard) {
        this.id = loyaltyCard.getId();
        this.valid = loyaltyCard.getValid();
        this.dateCreated = loyaltyCard.getDateCreated();
        this.totalOrder = loyaltyCard.getTotalOrder();
        this.ordersDone = loyaltyCard.getOrdersDone();
        this.restaurant = loyaltyCard.getRestaurant();
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public Long getId() {
        return id;
    }

    public Integer getOrdersDone() {
        return ordersDone;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public Boolean getValid() {
        return valid;
    }

    public LocalDate getValidThru() {
        return validThru;
    }
}
