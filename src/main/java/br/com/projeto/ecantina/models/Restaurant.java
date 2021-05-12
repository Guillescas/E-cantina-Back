package br.com.projeto.ecantina.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "restaurants")
public class Restaurant extends User{
    
    @Column(length = 18)
    private String cnpj;
    
    @Column
    private BigDecimal rating;
    
    @Column
    private Boolean open = false;
    
    @Column(length = 255)
    private String description;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Order> orders;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "establishment_id", referencedColumnName = "id")
    private Establishment establishment;

    public Restaurant() {}

    public Restaurant(String email, String password, String name) {
        super(email, password, name);
    }

    public Restaurant(String email, String password, String name, Establishment establishment) {
        super(email, password, name);
        this.establishment = establishment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Restaurant other = (Restaurant) obj;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        return true;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
