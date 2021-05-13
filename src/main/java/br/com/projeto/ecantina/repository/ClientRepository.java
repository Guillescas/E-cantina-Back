package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findByEmail(String username);
}
