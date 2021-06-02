package br.com.projeto.ecantina.dto.response;

import br.com.projeto.ecantina.models.Address;
import org.springframework.data.domain.Page;

public class ResponseAddressDto {

    private String street;

    private String cep;

    private String neighborhood;

    private Integer number;

    private String complement;

    public ResponseAddressDto(Address address) {
        this.street = address.getStreetName();
        this.cep = address.getCep();
        this.neighborhood = address.getNeighborhood();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }

    public String getStreet() {
        return street;
    }

    public String getCep() {
        return cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public static Page<ResponseAddressDto> convert(Page<Address> allAddress) {
        return allAddress.map(ResponseAddressDto::new);
    }
}
