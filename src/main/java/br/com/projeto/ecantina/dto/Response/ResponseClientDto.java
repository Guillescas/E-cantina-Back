package br.com.projeto.ecantina.dto.response;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResponseClientDto {
    
    private Long id;
    private String email;
    private String name;
    private String type;

    private String cpf;    
    private String urlImage;
    private List<Address> address;

    public ResponseClientDto(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.name = client.getName();
        this.type = client.getType();
        this.address = client.getAddress();
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Address> getAddress() {
        return address;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public static Page<ResponseClientDto> convert(Page<Client> users) {
        return users.map(ResponseClientDto::new);
    }
}
