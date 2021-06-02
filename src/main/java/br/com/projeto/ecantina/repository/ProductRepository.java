package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE p.restaurant_id = ?1")
    Page<Product> findByRestaurantId(Long id, Pageable pageable);
    
}
