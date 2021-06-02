package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {

    @Query(nativeQuery = true, value = "SELECT * FROM restaurants r WHERE r.establishment_id = ?1")
    Page<Restaurant> findEstablishmentRestaurants(Long establishmentId, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM restaurants r INNER JOIN user u ON r.id = u.id AND u.name LIKE %?1%")
    Page<Restaurant> findEstablishmentRestaurantsPerName(Long establishmentName, String restaurantName, Pageable pageable);

    Optional<Restaurant> findByEmail(String username);

    User findUserById(Long idUser);
}
