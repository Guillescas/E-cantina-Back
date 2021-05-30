package br.com.projeto.ecantina.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;

public interface CardRepository extends JpaRepository<Card, Long> {
    
    Page<Card> findByClient(Client client, Pageable pageable);
}
