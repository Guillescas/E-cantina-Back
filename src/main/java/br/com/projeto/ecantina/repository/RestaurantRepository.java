package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
