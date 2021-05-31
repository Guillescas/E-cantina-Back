package br.com.projeto.ecantina.dto.response.detailresponse;

import java.math.BigDecimal;
import java.util.List;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseDetailEstablishmentDto {

    private Long id;

    private String email;

    private String name;

    private String cnpj;

    private Integer capacity;

    private BigDecimal rent;

    private String urlImage;

    private List<Restaurant> restaurants;

    private Address address;

    public ResponseDetailEstablishmentDto(Establishment establishment) {
        this.id = establishment.getId();
        this.email = establishment.getEmail();
        this.name = establishment.getName();
        this.cnpj = establishment.getCnpj();
        this.capacity = establishment.getCapacity();
        this.rent = establishment.getRent();
        this.restaurants = establishment.getRestaurants();
        this.address = establishment.getAddress();
        this.urlImage = establishment.getUrlImage();
    }
    
    public String getUrlImage() {
        return urlImage;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
