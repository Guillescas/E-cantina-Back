package br.com.projeto.ecantina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order implements Serializable{

    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean finished = false;

    @Column(updatable = false, nullable = false)
    private LocalDate createdAt;

    @Column(updatable = false, nullable = false)
    private LocalDate finishAt;

    @Column(length = 300)
    private String observation;

    @Column(nullable = false)
    private Boolean valid = true;

    @Column
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private List<ProductList> productLists = new ArrayList<>();

    public Order() {}

    public Order(String observation, BigDecimal total) {
        this.observation = observation;
        this.total = total;
        this.createdAt = LocalDate.now();
        this.finishAt = LocalDate.now().plusDays(2);
    }

    public Order(String observation, BigDecimal total, List<ProductList> productLists) {
        this.observation = observation;
        this.total = total;
        this.productLists = productLists;
        this.createdAt = LocalDate.now();
        this.finishAt = LocalDate.now().plusDays(2);
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }
    
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
        if (this.createdAt.isBefore(this.finishAt) || !finished.booleanValue()) {
            return this.valid;
        } else {
            setValid(false);
            setFinished(true);
            return this.valid;
        }
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
