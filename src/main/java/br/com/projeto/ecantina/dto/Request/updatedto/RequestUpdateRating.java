package br.com.projeto.ecantina.dto.request.updatedto;

import java.math.BigDecimal;

import br.com.projeto.ecantina.models.Rating;

public class RequestUpdateRating {
    
    private BigDecimal value;

    private String description;

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Rating convert(Rating rating) {

        if (this.value != null)
            rating.setValue(getValue());
        if (this.description != null)
            rating.setDescription(getDescription());

        return rating;
    }
}
