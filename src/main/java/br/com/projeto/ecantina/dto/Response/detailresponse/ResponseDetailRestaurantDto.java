package br.com.projeto.ecantina.dto.response.detailresponse;

import java.util.List;

import br.com.projeto.ecantina.dto.response.ResponseDiscountCouponDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantOrdersDto;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseDetailRestaurantDto {

    private Long id;

    private String name;
    
    private List<Rating> rating;

    private Boolean paid;
    
    private Boolean open;
    
    private String description;

    private Category categories;

    private String urlImage;

    private List<ResponseRestaurantOrdersDto> orders;

    private List<Product> products;

    private List<ResponseDiscountCouponDto> discountCoupon;

    public ResponseDetailRestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.discountCoupon = ResponseDiscountCouponDto.convert(restaurant.getDiscountCoupon());
        this.categories = restaurant.getCategories();
        this.description = restaurant.getDescription();
        this.open = restaurant.getOpen();
        this.orders = ResponseRestaurantOrdersDto.convert(restaurant.getOrders());
        this.paid = restaurant.getPaid();
        this.products = restaurant.getProducts();
        this.rating = restaurant.getRatings();
        this.urlImage = restaurant.getUrlImage();
    }
    
    public Long getId() {
        return id;
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

    public List<ResponseRestaurantOrdersDto> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<ResponseDiscountCouponDto> getDiscountCoupon() {
        return discountCoupon;
    }
}
