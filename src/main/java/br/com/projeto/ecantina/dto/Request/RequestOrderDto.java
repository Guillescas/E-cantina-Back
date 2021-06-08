package br.com.projeto.ecantina.dto.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Order;
import br.com.projeto.ecantina.models.ProductList;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.DiscountCouponRepository;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestOrderDto {

    private Long clientId;

    private Long restaurantId;

    private Long discountId;

    private String observation;

    @JsonFormat(with = Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<RequestProductsListDto> productList;

    public Long getClientId() {
        return clientId;
    }

    public String getObservation() {
        return observation;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public List<RequestProductsListDto> getRequestProductLists() {
        return productList;
    }

    private BigDecimal getTotal(List<ProductList> productLists, Optional<DiscountCoupon> discount) {
        BigDecimal total = BigDecimal.valueOf(0);
        
        for (ProductList product : productLists) {
            total = total.add(product.getTotal());
        }

        if (discount.isPresent()) {
            if (Boolean.TRUE.equals(discount.get().getPercent())) {
                BigDecimal value = total.divide(discount.get().getValue());
                total = total.subtract(value);
            } else {
                total = total.subtract(discount.get().getValue());
            }
        }
        
        return total;
    }

    public Order convert(ClientRepository clientRepository, RestaurantRepository restaurantRepository,
            ProductRepository productRepository, DiscountCouponRepository discountCouponRepository) {

        Optional<DiscountCoupon> discount;
        Optional<Client> client = clientRepository.findById(getClientId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(getRestaurantId());
        if (this.discountId != null) {
            discount = discountCouponRepository.findById(getDiscountId());
        } else {
            discount = Optional.empty();
        }

        if(client.isPresent() && restaurant.isPresent()) {
            List<ProductList> productLists = createListProducts(productRepository);
            BigDecimal total = getTotal(productLists, discount);
            Order order = new Order(getObservation(), total, productLists, restaurant.get());

            client.get().getOrders().add(order);
            restaurant.get().getOrders().add(order);
            if (discount.isPresent()) {
                discount.get().getUsedByClients().add(client.get());
            }

            return order;
        } else {
            throw new NullPointerException("Restaurante/Cliente não encontrados");
        }

    }

    private List<ProductList> createListProducts(ProductRepository productRepository) {
        List<ProductList> productLists = new ArrayList<>();

        getRequestProductLists().forEach(pl -> {
            ProductList prodList = pl.convert(productRepository);
            productLists.add(prodList);
        });
        return productLists;
    }

}
