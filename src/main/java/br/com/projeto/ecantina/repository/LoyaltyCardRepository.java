package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.projeto.ecantina.models.LoyaltyCard;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long>, JpaSpecificationExecutor<LoyaltyCard> {
    
}
