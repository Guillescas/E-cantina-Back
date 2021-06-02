package br.com.projeto.ecantina.dto.request.updatedto;

import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.repository.ProductRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class RequestUpdateProductDto {
    
    @Size(max = 255)
    private String description;

    @NotNull
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product update(Long id, ProductRepository productRepository) {
        Product product = productRepository.getOne(id);
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        return product;
    }
}
