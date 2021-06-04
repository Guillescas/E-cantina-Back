package br.com.projeto.ecantina.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.ecantina.models.Client;
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

    private Client client;
    
    public ResponseLoyaltyCardDto(LoyaltyCard loyaltyCard) {
        this.id = loyaltyCard.getId();
        this.valid = loyaltyCard.getValid();
        this.dateCreated = loyaltyCard.getDateCreated();
        this.totalOrder = loyaltyCard.getTotalOrder();
        this.ordersDone = loyaltyCard.getOrdersDone();
        this.restaurant = loyaltyCard.getRestaurant();
        this.client = loyaltyCard.getClient();
    }
    
    public Client getClient() {
        return client;
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

    public static List<ResponseLoyaltyCardDto> convert(List<LoyaltyCard> loyaltyCards) {
        List<ResponseLoyaltyCardDto> responseLoyaltyCardDtos = new ArrayList<>();
        loyaltyCards.forEach(lc -> {
            responseLoyaltyCardDtos.add(new ResponseLoyaltyCardDto(lc));
        });
        return responseLoyaltyCardDtos;
    }
}
