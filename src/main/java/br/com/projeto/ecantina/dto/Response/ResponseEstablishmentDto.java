package br.com.projeto.ecantina.dto.response;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Establishment;

public class ResponseEstablishmentDto {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private Integer capacity;
    private BigDecimal rent;
    private String cnpj;
    private Address address;

    public ResponseEstablishmentDto(Establishment establishment) {
        this.id = establishment.getId();
        this.email = establishment.getEmail();
        this.password = establishment.getPassword();
        this.name = establishment.getName();
        this.capacity = establishment.getCapacity();
        this.cnpj = establishment.getCnpj();
        this.rent = establishment.getRent();
        this.address = establishment.getAddress();
    }

    public Address getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getCnpj() {
        return cnpj;
    }

    public BigDecimal getRent() {
        return rent;
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

    public static Page<ResponseEstablishmentDto> convert(Page<Establishment> users) {
        return users.map(ResponseEstablishmentDto::new);
    }
}
