package br.com.projeto.ecantina.dto.response.detailresponse;

import br.com.projeto.ecantina.models.Address;

public class ResponseDetailAddressDto {

    private Long id;

    private String streetName;

    private String cep;

    private String neighborhood;

    private Integer number;

    private String complement;

    public ResponseDetailAddressDto(Address address) {
        this.id = address.getId();
        this.streetName = address.getStreetName();
        this.cep = address.getCep();
        this.neighborhood = address.getNeighborhood();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }

    public String getCep() {
        return cep;
    }

    public String getComplement() {
        return complement;
    }

    public Long getId() {
        return id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStreetName() {
        return streetName;
    }
}
