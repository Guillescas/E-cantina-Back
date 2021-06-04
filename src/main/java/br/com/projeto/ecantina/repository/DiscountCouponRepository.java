package br.com.projeto.ecantina.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.ecantina.models.DiscountCoupon;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM discount_coupon dc INNER JOIN user u ON u.id = dc.restaurant_id WHERE u.name LIKE %?1%")
    List<DiscountCoupon> findByRestaurantName(String restaurantName);

    Optional<DiscountCoupon> findByCode(String code);
    
}
