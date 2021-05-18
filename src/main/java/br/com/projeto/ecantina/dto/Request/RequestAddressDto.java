package br.com.projeto.ecantina.dto.request;

import java.util.Optional;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;

public class RequestAddressDto {

    private Long clientId;

    private String street;

    private String cep;

    private String neighborhood;

    private Integer number;

    private String complement;

    public String getStreet() {
        return street;
    }

    public Long getClientId() {
        return clientId;
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

    public Address convert(ClientRepository clientRepository) {
        Optional<Client> client = clientRepository.findById(getClientId());

        if(client.isPresent()) {
            Address address = new Address(getStreet(), getCep(), getNeighborhood(), getNumber(), getComplement());
    
            client.get().getAddress().add(address);
            return address;
        } else {
            throw new NullPointerException("Cliente:Não foi encontrado");
        }
    }
}
