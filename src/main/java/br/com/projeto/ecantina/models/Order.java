package br.com.projeto.ecantina.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Boolean finished = false;

    @Column(updatable = false, nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(updatable = false, nullable = false)
    private LocalDate finishAt = LocalDate.now().plusDays(2);

    @Column(length = 300)
    private String observation;

    @Column(nullable = false)
    private Boolean valid = true;

    // @Column
    // private BigDecimal total;

    // @OneToMany
    // private List<ProductList> productLists;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    public Order() {}

    public Order(String observation) {
        this.observation = observation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
        Order other = (Order) obj;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (restaurant == null) {
            if (other.restaurant != null)
                return false;
        } else if (!restaurant.equals(other.restaurant))
            return false;
        return true;
    }

    // public BigDecimal getTotal() {
    //     return total;
    // }

    // public List<ProductList> getProductLists() {
    //     return productLists;
    // }

    // public void setTotal(BigDecimal total) {
    //     this.total = total;
    // }

    // public void setProductLists(List<ProductList> productLists) {
    //     this.productLists = productLists;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDate finishAt) {
        this.finishAt = finishAt;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
