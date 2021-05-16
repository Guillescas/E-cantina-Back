package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByIdAndType(Long id, String type);
}
