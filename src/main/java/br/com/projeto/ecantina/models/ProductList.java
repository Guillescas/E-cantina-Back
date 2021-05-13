package br.com.projeto.ecantina.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "products_list")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column()
    private Integer quantity;

    @Column
    private BigDecimal value;

    public ProductList() {}

    // public ProductList(Product product, Order order, Integer quantity) {
    //     this.product = product;
    //     this.quantity = quantity;
    //     this.quantity = quantity;
    //     this.value = product.getPrice();
    // }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((id == null) ? 0 : id.hashCode());
    //     result = prime * result + ((order == null) ? 0 : order.hashCode());
    //     result = prime * result + ((product == null) ? 0 : product.hashCode());
    //     return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     ProductList other = (ProductList) obj;
    //     if (id == null) {
    //         if (other.id != null)
    //             return false;
    //     } else if (!id.equals(other.id))
    //         return false;
    //     if (order == null) {
    //         if (other.order != null)
    //             return false;
    //     } else if (!order.equals(other.order))
    //         return false;
    //     if (product == null) {
    //         if (other.product != null)
    //             return false;
    //     } else if (!product.equals(other.product))
    //         return false;
    //     return true;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    // public Product getProduct() {
    //     return product;
    // }

    // public void setProduct(Product product) {
    //     this.product = product;
    // }

    // public Order getOrder() {
    //     return order;
    // }

    // public void setOrder(Order order) {
    //     this.order = order;
    // }
}
