package br.com.projeto.ecantina.dto.response.detailresponse;

import java.math.BigDecimal;
import java.util.List;

import br.com.projeto.ecantina.dto.response.ResponseDiscountCouponDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantOrdersDto;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.models.Restaurant;

public class ResponseDetailRestaurantDto {

    private Long id;
    private String name;
    private List<Rating> ratings;
    private Boolean paid;
    private Boolean open;
    private String description;
    private Category categories;
    private String urlImage;
    private List<ResponseRestaurantOrdersDto> orders;
    private List<Product> products;
    private List<ResponseDiscountCouponDto> discountCoupon;
    private BigDecimal averageRating;

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
        this.ratings = restaurant.getRatings();
        this.urlImage = restaurant.getUrlImage();
        if (restaurant.getRatings() != null && !restaurant.getRatings().isEmpty()) {
            this.averageRating = calculateAverage(this.ratings);
        }
    }

    public BigDecimal calculateAverage(List<Rating> ratings) {
        if (!getRatings().isEmpty() && getRatings().size() != 0) {
            BigDecimal totalSum = BigDecimal.ZERO;
            for (Rating rating : ratings) {
                totalSum = totalSum.add(rating.getValue());
            }
            return totalSum.divide(new BigDecimal(ratings.size()));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
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

    public List<Rating> getRatings() {
        return ratings;
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
