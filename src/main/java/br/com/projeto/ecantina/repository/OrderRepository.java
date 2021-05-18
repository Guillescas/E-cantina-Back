package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
