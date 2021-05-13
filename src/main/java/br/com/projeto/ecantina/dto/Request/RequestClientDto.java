package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.User;

public class RequestClientDto {
    
    private String email;
    private String password;
    private String name;
    private String type;

    private String establishmentName;
    private String cnpj;

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
        return establishmentName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public User convertClient() {
        return new Client(getEmail(), getPassword(), getName());
    }
}