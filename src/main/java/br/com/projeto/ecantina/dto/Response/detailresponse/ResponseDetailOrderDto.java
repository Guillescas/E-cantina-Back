package br.com.projeto.ecantina.dto.response.detailresponse;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.projeto.ecantina.models.Order;

public class ResponseDetailOrderDto {

    private Boolean finished;

    private LocalDate createdAt;

    private LocalDate finishAt;

    private String observation;

    private Boolean valid = true;

    private BigDecimal total;

    public ResponseDetailOrderDto(Order order) {
        this.createdAt = order.getCreatedAt();
        this.finishAt = order.getFinishAt();
        this.finished = order.getFinished();
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

    public String getObservation() {
        return observation;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Boolean getValid() {
        return valid;
    }
}
