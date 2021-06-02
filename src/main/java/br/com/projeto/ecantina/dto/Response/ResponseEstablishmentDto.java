package br.com.projeto.ecantina.dto.response;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Establishment;
import org.springframework.data.domain.Page;

public class ResponseEstablishmentDto {
    
    private Long id;
    private String email;
    private String name;
    private Integer capacity;
    private Address address;
    private String urlImage;

    public ResponseEstablishmentDto(Establishment establishment) {
        this.id = establishment.getId();
        this.email = establishment.getEmail();
        this.name = establishment.getName();
        this.capacity = establishment.getCapacity();
        this.address = establishment.getAddress();
        this.urlImage = establishment.getUrlImage();
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Page<ResponseEstablishmentDto> convert(Page<Establishment> users) {
        return users.map(ResponseEstablishmentDto::new);
    }
}
