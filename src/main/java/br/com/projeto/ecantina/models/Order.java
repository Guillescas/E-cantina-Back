package br.com.projeto.ecantina.models;

import java.time.LocalDate;
import java.util.List;

public class Order {
    
    private Long id;

    private Client client;

    private List<Product> products;

    private String observation;

    private Boolean valid = true;

    private Boolean finished = false;

    private LocalDate createDate = LocalDate.now();

    private LocalDate finishDate = LocalDate.now().plusDays(2);

    public Order() {}

    public Order(Client client, List<Product> products, String observation) {
        this.client = client;
        this.products = products;
        this.observation = observation;
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
