package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.ecantina.models.DiscountCoupon;

public class ResponseDiscountCouponDto {

    private Long id;

    private String code;

    private BigDecimal value;

    private LocalDate finishedAt;

    private Boolean valid;

    public ResponseDiscountCouponDto(DiscountCoupon discountCoupon) {
        this.id = discountCoupon.getId();
        this.code = discountCoupon.getCode();
        this.value = discountCoupon.getValue();
        this.finishedAt = discountCoupon.getFinishedAt();
        this.valid = discountCoupon.getValid(null);
    }

    public Boolean getValid() {
        return valid;
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
