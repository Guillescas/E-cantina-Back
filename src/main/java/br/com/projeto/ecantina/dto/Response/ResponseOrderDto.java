package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Order;
import br.com.projeto.ecantina.models.ProductList;

public class ResponseOrderDto {
    
    private Boolean finished = false;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate finishAt = LocalDate.now().plusDays(2);

    private String observation;

    private Boolean valid = true;

    private BigDecimal total;

    private List<ProductList> productLists;

    public ResponseOrderDto(Order order) {
        this.finished = order.getFinished();
        this.createdAt = order.getCreatedAt();
        this.finishAt = order.getFinishAt();
        this.observation = order.getObservation();
        this.valid = order.getValid();
        this.total = order.getTotal();
        this.productLists = order.getProductLists();
    }

    public Boolean getFinished() {
        return finished;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDate finishAt) {
        this.finishAt = finishAt;
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
