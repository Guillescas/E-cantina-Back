package br.com.projeto.ecantina.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

public class RequestRestaurantDto {
    
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "{password.size}")
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @CNPJ
    private String cnpj;

    @Size(max = 255)
    private String description;
    
    private String establishmentName;

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant convert(EstablishmentRepository establishmentRepository) {
        Establishment establishment = establishmentRepository.findByName(establishmentName);
        Restaurant restaurant = new Restaurant(getEmail(), getPassword(), getName());
        
        establishment.getRestaurants().add(restaurant);

        return restaurant;
    }   
}
