package br.com.projeto.ecantina.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "establishments")
public class Establishment extends User {
    
    @Column(length = 18)
    private String cnpj;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private BigDecimal rent;

    @OneToMany
    @JoinColumn(name = "establishment_id")
    private List<Restaurant> restaurants;

    @OneToOne
    private Address address;

    
    public Establishment() {}

    public Establishment(String email, String password, String name, Integer capacity) {
        super(email, password, name);
        this.capacity = capacity;
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
