package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>{

    @Query(nativeQuery = true, value = "SELECT * FROM orders o WHERE o.client_id = ?1")
    Page<Order> findByClient(Long clientId, Pageable pageable);

    Page<Order> findAll(Pageable pageable);
    
}
