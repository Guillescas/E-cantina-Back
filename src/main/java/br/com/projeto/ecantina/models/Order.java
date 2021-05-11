package br.com.projeto.ecantina.models;

import java.time.LocalDate;
import java.util.List;

public class Order {
    
    private Long id;

    private Restaurant restaurant;

    private Client client;

    private List<Product> products;

    private String observation;

    private Boolean valid = true;

    private Boolean finished = false;

    private LocalDate createDate = LocalDate.now();

    private LocalDate finishDate = LocalDate.now().plusDays(2);

    public Order() {}

    public Order(Restaurant restaurant, Client client, List<Product> products, String observation) {
        this.setRestaurant(restaurant);
        this.client = client;
        this.products = products;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    
}
