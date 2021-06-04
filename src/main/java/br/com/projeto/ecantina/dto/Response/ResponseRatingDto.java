package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;

import br.com.projeto.ecantina.models.Rating;

public class ResponseRatingDto {
    
    private Long id;

    private BigDecimal value;

    private String description;

    public ResponseRatingDto(Rating rating) {
        this.id = rating.getId();
        this.value = rating.getValue();
        this.description = rating.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }
}
