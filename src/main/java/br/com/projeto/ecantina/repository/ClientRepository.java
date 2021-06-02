package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findByEmail(String username);

    User findUserById(Long id);

    Optional<Client> findByCpf(String cpf);
}
