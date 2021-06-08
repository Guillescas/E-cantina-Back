package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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

    private ResponseRestaurantDto restaurantDto;

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
        this.restaurantDto = new ResponseRestaurantDto(order.getRestaurant());
    }

    public ResponseRestaurantDto getRestaurantDto() {
        return restaurantDto;
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
}
