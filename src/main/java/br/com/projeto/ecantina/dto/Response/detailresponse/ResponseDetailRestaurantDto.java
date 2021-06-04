package br.com.projeto.ecantina.dto.response.detailresponse;

import java.util.List;
import java.util.Set;

import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Order;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseDetailRestaurantDto {

    private String name;
    
    private List<Rating> rating;

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
        this.rating = restaurant.getRatings();
        this.urlImage = restaurant.getUrlImage();
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getName() {
        return name;
    }

    public List<Rating> getRating() {
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
