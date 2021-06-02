package br.com.projeto.ecantina.repository;

import br.com.projeto.ecantina.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
