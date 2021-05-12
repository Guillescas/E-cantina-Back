package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

public class RequestUserDto {
    
    private String email;
    private String password;
    private String name;
    private String type;

    private String establishment;

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

    public String getType() {
        return type;
    }

    public String getEstablishment() {
        return establishment;
    }

    public User convertClient() {
        return new Client(getEmail(), getPassword(), getName());
    }

    public User convert(EstablishmentRepository establishmentRepository) {
        Establishment establishment = establishmentRepository.findByName(getEstablishment());

        return new Restaurant(email, password, name, establishment);
    }
}