package br.com.projeto.ecantina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.ecantina.models.DiscountCoupon;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM discount_coupon dc WHERE restaurant_id = ?1")
    List<DiscountCoupon> findByRestaurantId(Long restaurantId);
    
}
