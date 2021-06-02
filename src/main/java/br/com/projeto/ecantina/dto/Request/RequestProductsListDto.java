package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.ProductList;
import br.com.projeto.ecantina.repository.ProductRepository;

import java.util.Optional;

public class RequestProductsListDto {
    
    private Long productId;

    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public ProductList convert(ProductRepository productRepository) {
        Optional<Product> product = productRepository.findById(getProductId());

        if(product.isPresent()) {
            return new ProductList(getQuantity(), product.get());
        } else {
            throw new NullPointerException("Produto:Não foi encontrado esse produto");
        }
    }
}
