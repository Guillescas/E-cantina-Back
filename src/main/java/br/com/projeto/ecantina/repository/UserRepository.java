package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByIdAndType(Long id, String type);

    Optional<User> findByEmail(String email);
}
