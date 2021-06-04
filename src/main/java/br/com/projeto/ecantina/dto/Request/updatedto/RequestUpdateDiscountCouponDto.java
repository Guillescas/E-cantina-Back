package br.com.projeto.ecantina.dto.request.updatedto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.ProductRepository;

public class RequestUpdateDiscountCouponDto {

    private BigDecimal value;

    private Long clientId;

    @JsonFormat(with = Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> productsIds;

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public Long getClientId() {
        return clientId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public DiscountCoupon update(DiscountCoupon discountCoupon, ProductRepository productRepository, ClientRepository clientRepository) {

        if (this.productsIds != null) {
            productsIds.forEach(prodId -> {
                Optional<Product> productFind = productRepository.findById(prodId);
                if (productFind.isPresent())
                    discountCoupon.getProducts().add(productFind.get());
            });
        } else if (this.clientId != null) {
            Optional<Client> clinetFind = clientRepository.findById(getClientId());
            discountCoupon.getUsedByClients().add(clinetFind.get());
        } else if (this.value != null)
            discountCoupon.setValue(getValue());
        


        return discountCoupon;
    }
}
