package br.com.projeto.ecantina.dto.response.detailresponse;

import br.com.projeto.ecantina.models.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ResponseDetailRestaurantDto {

    private String name;
    
    private BigDecimal rating;

    private Boolean paid;
    
    private Boolean open;
    
    private String description;

    private Category categories;

    private String urlImage;

    private Set<Order> orders;

    private List<Product> products;

    private List<DiscountCoupon> discountCoupon;

    public ResponseDetailRestaurantDto(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.discountCoupon = restaurant.getDiscountCoupon();
        this.categories = restaurant.getCategories();
        this.description = restaurant.getDescription();
        this.open = restaurant.getOpen();
        this.orders = restaurant.getOrders();
        this.paid = restaurant.getPaid();
        this.products = restaurant.getProducts();
        this.rating = restaurant.getRating();
        this.urlImage = restaurant.getUrlImage();
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Boolean getPaid() {
        return paid;
    }

    public Boolean getOpen() {
        return open;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategories() {
        return categories;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<DiscountCoupon> getDiscountCoupon() {
        return discountCoupon;
    }
}
