package br.com.projeto.ecantina.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal value;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Rating() {}

    public Rating(BigDecimal value, String description, Restaurant restaurant) {
        this.value = value;
        this.description = description;
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
