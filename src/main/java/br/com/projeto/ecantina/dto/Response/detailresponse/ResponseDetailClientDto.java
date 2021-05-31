package br.com.projeto.ecantina.dto.response.detailresponse;

import java.util.List;
import java.util.Set;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.models.Order;

public class ResponseDetailClientDto {
    
    private String email;
    private String name;
    private String type;
    private String cpf;

    private List<Address> address;
    private Set<Order> orders;
    private List<Card> cards;
    private List<LoyaltyCard> loyaltyCards;
    private String urlImage;

    public ResponseDetailClientDto(Client client) {
        this.email = client.getEmail();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.type = client.getType();
        this.address = client.getAddress();
        this.orders = client.getOrders();
        this.cards = client.getCards();
        this.loyaltyCards = client.getLoyaltyCards();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Address> getAddress() {
        return address;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<LoyaltyCard> getLoyaltyCards() {
        return loyaltyCards;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
