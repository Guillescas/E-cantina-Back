package br.com.projeto.ecantina.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.validation.notations.EmailEquals;
import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.UserType;

public class RequestEstablishmentDto {

    // Establishment
    @Email(message = "{email.format}")
    @NotBlank(message = "{email.blank}")
    @EmailEquals(message = "{email.equals}")
    private String email;

    @NotBlank(message = "{password.blank}")
    @Size(min = 8, message = "{password.size}")
    private String password;

    @NotBlank(message = "{name.blank}")
    private String name;
    
    @NotNull(message = "{capacity.null}")
    @Positive(message = "{capacity.positive}")
    private Integer capacity;

    @NotNull(message = "{rent.null}")
    @Positive(message = "{rent.positive}")
    private BigDecimal rent;


    @NotBlank(message = "{cnpj.blank}")
    private String cnpj;

    // Address
    @NotBlank(message = "{street.blank}")
    @Size(max = 100, message = "{street.size}")
    private String street;

    @NotNull(message = "{number.null}")
    private Integer number;

    @Size(max = 30, message = "{complement.size}")
    private String complement;

    @NotBlank(message = "{neighborhood.blank}")
    @Size(max = 50, message = "{neighborhood.size}")
    private String neighborhood;

    @NotBlank(message = "{cep.blank}")
    @Size(max = 9, message = "{cep.size}")
    private String cep;

    public String getEmail() {
        return email;
    }

    public String getCep() {
        return cep;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public Establishment convert() {
        Address address = new Address(getStreet(), getCep(), getNeighborhood(), getNumber(), getComplement());
        Establishment establishment = new Establishment(getEmail(), getPassword(), getName(), getCnpj(), getCapacity(), getRent(), address);
        establishment.getUserTypes().add(new UserType("ROLE_CLIENT"));
        return establishment;
    }
}
