package br.com.projeto.ecantina.dto.response;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;

public class ResponseUserDto {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private String type;

    // User attribute
    private String cpf;    
    private String urlImagem;
    private Address address;

    public ResponseUserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }

    public ResponseUserDto(User user, Client client) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }

    public ResponseUserDto(User user, Restaurant restaurant) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }

    // public ResponseUserDto(User user, Establishment establishment) {
    //     this.id = user.getId();
    //     this.email = user.getEmail();
    //     this.password = user.getPassword();
    //     this.name = user.getName();
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

}
