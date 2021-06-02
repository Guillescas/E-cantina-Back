package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.BankData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankDataRepository extends JpaRepository<BankData, Long> {
    
    Optional<BankData> findByName(String name);
}
