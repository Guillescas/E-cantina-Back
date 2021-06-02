package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM card c WHERE c.client_id = ?1")
    Page<Card> findByClient(Long clientId, Pageable pageable);
}
