package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.BankData;

public interface BankDataRepository extends JpaRepository<BankData, Long> {
    
    Optional<BankData> findByName(String name);
}
