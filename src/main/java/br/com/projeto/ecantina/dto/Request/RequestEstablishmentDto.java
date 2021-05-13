package br.com.projeto.ecantina.dto.request;

import java.math.BigDecimal;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Establishment;

public class RequestEstablishmentDto {

    // Establishment
    private String email;
    private String password;
    private String name;
    private Integer capacity;
    private BigDecimal rent;
    private String cnpj;

    // Address
    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
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
        // addressRepository.sav
        return new Establishment(getEmail(), getPassword(), getName(), getCnpj(), getCapacity(), getRent(), address);
    }
}
