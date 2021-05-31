package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Product;

public class ResponseProductDto {
    
    private Long id;
    private String type;
    private String name;
    private String description;
    private BigDecimal price;

    public ResponseProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.type = product.getType();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static Page<ResponseProductDto> convert(Page<Product> products) {
        return products.map(ResponseProductDto::new);
    } 
}
