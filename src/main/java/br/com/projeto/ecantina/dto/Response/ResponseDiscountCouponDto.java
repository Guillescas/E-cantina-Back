package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.models.Product;

public class ResponseDiscountCouponDto {

    private Long id;

    private String code;

    private BigDecimal value;

    private Boolean used;

    private LocalDate finishedAt;

    private List<Product> products;

    public ResponseDiscountCouponDto(DiscountCoupon discountCoupon) {
        this.id = discountCoupon.getId();
        this.code = discountCoupon.getCode();
        this.value = discountCoupon.getValue();
        this.finishedAt = discountCoupon.getFinishedAt();
        this.products = discountCoupon.getProducts();
        this.used = discountCoupon.getUsed();
    }

    public Boolean getUsed() {
        return used;
    }
    
    public String getCode() {
        return code;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }
    
    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getValue() {
        return value;
    }

    public static List<ResponseDiscountCouponDto> convert(List<DiscountCoupon> allDiscountCoupon) {
        List<ResponseDiscountCouponDto> lDtos= new ArrayList<>();
        allDiscountCoupon.forEach(dc -> {
            lDtos.add(new ResponseDiscountCouponDto(dc));
        });
        return lDtos;
    }
}
