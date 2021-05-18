package br.com.projeto.ecantina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);
    
}
