package br.com.projeto.ecantina.dto.request.updatedto;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;

public class RequestUpdateEstablishmentDto {
    
    private String email;

    private String name;

    private Integer capacity;

    private String cnpj;

    private BigDecimal rent;

    public Integer getCapacity() {
        return capacity;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRent() {
        return rent;
    }

    private void changeAttributes(Establishment establishment) {

        if(this.cnpj != null && !getCnpj().isBlank()) 
            establishment.setCnpj(getCnpj());
        if(this.name != null && !getName().isBlank()) 
            establishment.setName(getName());
        if(this.capacity != null && getCapacity().intValue() != 0)
            establishment.setCapacity(getCapacity());
        if(this.rent != null && getRent().intValue() != 0)
            establishment.setRent(getRent());
            
    }

    public Establishment update(Optional<Establishment> establishmentFind, UserRepository userRepository) {

        Establishment establishment = establishmentFind.get();
        if (!establishment.getEmail().equals(getEmail()) && this.email != null) {
            Optional<User> user = userRepository.findByEmail(getEmail());
            if (user.isPresent())
                throw new EmailNotValidException("Email já está em uso");
            else
                establishment.setEmail(getEmail());
        }

        changeAttributes(establishment);


        return establishment;
    }

}
