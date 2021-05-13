package br.com.projeto.ecantina.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.ecantina.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM restaurants r WHERE r.establishment_id = ?1")
    Page<Restaurant> findEstablishmentRestaurants(Long establishmentName, Pageable pageable);
}
