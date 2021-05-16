package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
