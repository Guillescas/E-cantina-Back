package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.ecantina.models.Order;

public class ResponseRestaurantOrdersDto {

    private Long id;

    private Boolean finished;

    private LocalDate createdAt;

    private LocalDate finishAt;

    private String observation;

    private Boolean valid;

    private BigDecimal total;

    public ResponseRestaurantOrdersDto(Order order) {
        this.id = order.getId();
        this.finished = order.getFinished();
        this.createdAt = order.getCreatedAt();
        this.finishAt = order.getFinishAt();
        this.observation = order.getObservation();
        this.valid = order.getValid();
        this.total = order.getTotal();
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getFinishAt() {
        return finishAt;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Long getId() {
        return id;
    }

    public String getObservation() {
        return observation;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Boolean getValid() {
        return valid;
    }

    public static List<ResponseRestaurantOrdersDto> convert(List<Order> orders) {
        List<ResponseRestaurantOrdersDto> restaurantOrdersDtos = new ArrayList<>();

        orders.forEach(order -> {
            restaurantOrdersDtos.add(new ResponseRestaurantOrdersDto(order));
        });

        return restaurantOrdersDtos;
    }
}
