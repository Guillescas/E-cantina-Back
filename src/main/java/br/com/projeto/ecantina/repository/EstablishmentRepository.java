package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.User;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long>{
    
    Establishment findByName(String establishment);

    Optional<Establishment> findByEmail(String username);

    User findUserById(Long idUser);
}
