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
        this.urlImage = client.getUrlImage();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<LoyaltyCard> getLoyaltyCards() {
        return loyaltyCards;
    }

    public void setLoyaltyCards(List<LoyaltyCard> loyaltyCards) {
        this.loyaltyCards = loyaltyCards;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
