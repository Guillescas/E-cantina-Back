package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

public class RequestRestaurantDto {
    
    private String email;
    private String password;
    private String name;
    private String cnpj;
    private String type;
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
