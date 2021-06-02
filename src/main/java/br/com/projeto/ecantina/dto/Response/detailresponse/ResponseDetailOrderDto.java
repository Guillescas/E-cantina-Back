package br.com.projeto.ecantina.dto.response.detailresponse;

import br.com.projeto.ecantina.models.Order;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class ResponseDetailOrderDto {

    private Boolean finished;

    private String createdAt;

    private String finishAt;

    private String observation;

    private Boolean valid;

    private BigDecimal total;

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseDetailOrderDto(Order order) {
        this.createdAt = order.getCreatedAt().format(formatter);
        this.finishAt = order.getFinishAt().format(formatter);
        this.finished = order.getFinished();
        this.observation = order.getObservation();
        this.valid = order.getValid();
        this.total = order.getTotal();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFinishAt() {
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
