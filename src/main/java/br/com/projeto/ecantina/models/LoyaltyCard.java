package br.com.projeto.ecantina.models;

import javax.persistence.*;

import br.com.projeto.ecantina.config.exceptions.InvalidLoyaltyCardException;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "loyalty_card")
public class LoyaltyCard implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean valid;

    @Column(updatable = false, nullable = false)
    private LocalDate dateCreated;

    @Column(updatable = false, nullable = false)
    private LocalDate validThru;

    @Column(nullable = false, updatable = false)
    private Integer totalOrder;

    @Column(nullable = false)
    private Integer ordersDone;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Restaurant restaurant;

    public LoyaltyCard() {
    }

    public LoyaltyCard(LocalDate dateCreated, LocalDate validThru, Integer totalOrder, Restaurant restaurant) {
        this.valid = getValid();
        this.dateCreated = dateCreated;
        this.validThru = validThru;
        this.totalOrder = totalOrder;
        this.restaurant = restaurant;
        this.ordersDone = 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoyaltyCard other = (LoyaltyCard) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getOrdersDone() {
        return ordersDone;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValid() {
        if (getValidThru().isAfter(getDateCreated()))
            return true;
        else
            return false;
    }

    public void increaseOrdersDone() {
        if (this.getOrdersDone() >= this.getTotalOrder())
            this.ordersDone++;
        else
            throw new InvalidLoyaltyCardException("Limite do cartão já atingido");
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getValidThru() {
        return validThru;
    }

    public void setValidThru(LocalDate validThru) {
        this.validThru = validThru;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }
}
