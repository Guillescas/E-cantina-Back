package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByIdAndType(Long id, String type);

    Optional<User> findByEmail(String email);
}
