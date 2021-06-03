package br.com.projeto.ecantina.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity(name = "restaurants")
public class Restaurant extends User{
    
    @Column(length = 18)
    private String cnpj;
    
    @Column
    private BigDecimal rating;

    private Boolean paid;
    
    @Column
    private Boolean open;
    
    @Column(length = 255)
    private String description;

    @ManyToOne
    private Establishment establishment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category categories;

    // @OneToMany
    // @JoinColumn(name = "restaurant_id")
    // private Set<LoyaltyCard> loyaltyCards;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private Set<Order> orders;
    
    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Product> products;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<DiscountCoupon> discountCoupon;

    public Restaurant() {}

    public Restaurant(String email, String password, String name, Category category, String description) {
        super(email, password, name, "restaurant");
        this.categories = category;
        this.description = description;
        this.open = false;
        this.paid = true;
    }

    public Restaurant(String email, String password, String name, String cnpj, Category category, String description) {
        super(email, password, name, "restaurant");
        this.cnpj = cnpj;
        this.categories = category;
        this.description = description;
        this.open = false;
        this.paid = true;
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

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public Boolean getPaid() {
        return paid;
    }

    public List<DiscountCoupon> getDiscountCoupon() {
        return discountCoupon;
    }

    // public Set<LoyaltyCard> getLoyaltyCards() {
    //     return loyaltyCards;
    // }

    public Set<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
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

    public Category getCategories() {
        return categories;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return this.paid;
    }
}
