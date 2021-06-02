package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);
    
}
