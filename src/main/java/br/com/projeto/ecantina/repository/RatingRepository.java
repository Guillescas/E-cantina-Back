package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.ecantina.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM rating r WHERE client_id = ?1 AND restaurant_id = ?2")
    Optional<Rating> findByClientAndRestaurant(Long clientId, Long restaurantId);
}
