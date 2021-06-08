package br.com.projeto.ecantina.dto.response.detailresponse;

import java.util.List;

import br.com.projeto.ecantina.dto.response.ResponseRestaurantOrdersDto;
import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;

public class ResponseDetailClientDto {
    
    private String email;
    private String name;
    private String type;
    private String cpf;

    private List<Address> address;
    private List<ResponseRestaurantOrdersDto> orders;
    private List<Card> cards;
    private String urlImage;

    public ResponseDetailClientDto(Client client) {
        this.email = client.getEmail();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.type = client.getType();
        this.address = client.getAddress();
        this.orders = ResponseRestaurantOrdersDto.convert(client.getOrders());
        this.cards = client.getCards();
        this.urlImage = client.getUrlImage();
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

    public List<ResponseRestaurantOrdersDto> getOrders() {
        return orders;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
