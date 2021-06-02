package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class RequestAddressDto {

    @NotNull(message = "{id.null}")
    private Long clientId;

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
