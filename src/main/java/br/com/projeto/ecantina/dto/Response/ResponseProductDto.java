package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Product;

public class ResponseProductDto {
    
    private Long id;
    private String type;
    private String name;
    private String description;
    private BigDecimal price;
    private String urlImage;

    public ResponseProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.type = product.getType();
        this.price = product.getPrice();
        this.urlImage = product.getUrlImage();
    }

    public String getUrlImage() {
        return urlImage;
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

    public static List<ResponseProductDto> convert(List<Product> products) {
        List<ResponseProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            productDtos.add(new ResponseProductDto(product));
        });
        return productDtos;
    } 
}
