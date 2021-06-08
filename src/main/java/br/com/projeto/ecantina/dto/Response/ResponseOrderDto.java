package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Order;
import br.com.projeto.ecantina.models.ProductList;

public class ResponseOrderDto {
    
    private Boolean finished = false;

    private String createdAt;

    private String finishAt;

    private String observation;

    private Boolean valid = true;

    private BigDecimal total;

    private List<ProductList> productLists;

    private ResponseRestaurantDto restaurant;

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseOrderDto(Order order) {
        this.finished = order.getFinished();
        this.createdAt = order.getCreatedAt().format(formatter);
        this.finishAt = order.getFinishAt().format(formatter);
        this.observation = order.getObservation();
        this.valid = order.getValid();
        this.total = order.getTotal();
        this.productLists = order.getProductLists();
        this.restaurant = new ResponseRestaurantDto(order.getRestaurant());
    }

    public ResponseRestaurantDto getRestaurant() {
        return restaurant;
    }

    public Boolean getFinished() {
        return finished;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public String getObservation() {
        return observation;
    }

    public Boolean getValid() {
        return valid;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }

    public static Page<ResponseOrderDto> convert(Page<Order> orders) {
        return orders.map(ResponseOrderDto::new);
    } 
    
    public static List<ResponseOrderDto> convert(List<Order> orders) {
        List<ResponseOrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> {
            ordersDto.add(new ResponseOrderDto(order));
        });

        return ordersDto;
    }   
}
