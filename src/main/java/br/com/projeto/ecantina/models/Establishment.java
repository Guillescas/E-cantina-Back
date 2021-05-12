package br.com.projeto.ecantina.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Establishment extends User{
    
    @Column(length = 18)
    private String cnpj;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private BigDecimal rent;

    @OneToMany(mappedBy = "establishment")
    private List<Restaurant> restaurants;

    public Establishment() {}

    public Establishment(String email, String password, String name, Integer capacity) {
        super(email, password, name);
        this.capacity = capacity;
    }
}
