package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long>{
    
    Optional<Establishment> findByName(String establishment);

    Optional<Establishment> findByEmail(String username);

    User findUserById(Long idUser);
}
