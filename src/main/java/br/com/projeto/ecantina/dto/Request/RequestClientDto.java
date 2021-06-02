package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.config.validation.notations.EmailEquals;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestClientDto {

    @Email(message = "{email.format}")
    @NotBlank(message = "{email.blank}")
    @EmailEquals(message = "{email.equals}")
    private String email;

    @NotBlank(message = "{password.blank}")
    @Size(min = 8, message = "{password.size}")
    private String password;

    @NotBlank(message = "{name.blank}")
    private String name;

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

    public Client convertClient() {
        Client client = new Client(getEmail(), getPassword(), getName());
        client.getUserTypes().add(new UserType("ROLE_CLIENT"));
        return client;
    }
}