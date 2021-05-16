package br.com.projeto.ecantina.dto.response;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Client;

public class ResponseClientDto {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private String type;

    private String cpf;    
    private String urlImage;
    private Address address;

    public ResponseClientDto(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.name = client.getName();
        this.type = client.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public static Page<ResponseClientDto> convert(Page<Client> users) {
        return users.map(ResponseClientDto::new);
    }
}
