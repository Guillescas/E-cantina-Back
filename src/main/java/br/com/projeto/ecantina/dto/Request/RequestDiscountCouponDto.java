package br.com.projeto.ecantina.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import br.com.projeto.ecantina.config.validation.notations.NameEquals;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestDiscountCouponDto {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NameEquals
    @Size(min = 6, max = 6,message = "${code.size}")
    private String code;

    @DecimalMax(value = "100", message = "${valueCopoun.decimalMax}")
    @DecimalMin(value = "0.1", message = "${valueCopoun.decimalMin}")
    private BigDecimal value;

    private String finishedAt;

    @NotNull
    private Long restaurantId;

    @JsonFormat(with = Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> productsIds;

    public String getCode() {
        return code;
    }

    @Future
    public LocalDate getFinishedAt() {
        return LocalDate.parse(this.finishedAt, formatter);
    }

    public List<Long> getProducts() {
        return productsIds;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public DiscountCoupon convert(RestaurantRepository restaurantRepository) {

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(getRestaurantId());

        if(restaurantFind.isPresent()) {
            List<Product> productsList = createListProducts(getProducts(), restaurantFind.get());
            DiscountCoupon discountCoupon = new DiscountCoupon(getCode(), getValue(), getFinishedAt(), productsList);
            restaurantFind.get().getDiscountCoupon().add(discountCoupon);
            return discountCoupon;
        } 

        throw new NullPointerException("Restaurante não encontrado!");
    }

    private List<Product> createListProducts(List<Long> pList, Restaurant restaurant) {
        List<Product> productList = new ArrayList<>();
        List<Product> productListRestaurant = restaurant.getProducts();

        productListRestaurant.forEach(product -> {
            pList.forEach(p -> {
                if (product.getId().equals(p)) {
                    productList.add(product);
                }
            });
        });
        
        return productList;
    }
    
}
