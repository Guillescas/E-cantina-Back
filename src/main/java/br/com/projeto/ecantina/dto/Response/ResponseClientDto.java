package br.com.projeto.ecantina.dto.response;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Client;

public class ResponseClientDto {
    
    private Long id;
    private String email;
    private String name;
    private String type;

    private String cpf;    
    private String urlImage;

    public ResponseClientDto(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.name = client.getName();
        this.type = client.getType();
        this.cpf = client.getCpf();
        this.urlImage = client.getUrlImage();
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
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
