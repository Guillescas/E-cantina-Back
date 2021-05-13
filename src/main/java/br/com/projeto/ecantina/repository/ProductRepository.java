package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
