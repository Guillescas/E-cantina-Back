package br.com.projeto.ecantina.dto.request.updatedto;

import java.util.Optional;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.repository.ClientRepository;

public class RequestUpdateLoyaltyCardDto {

    private Integer ordersDone;

    private Long clientId;

    public Long getClientId() {
        return clientId;
    }

    public Integer getOrdersDone() {
        return ordersDone;
    }

    public Boolean getValid(LoyaltyCard loyaltyCard) {
        return loyaltyCard.getValid();
    }

    public LoyaltyCard convert(LoyaltyCard loyaltyCard, ClientRepository clientRepository) {

        Optional<Client> clientFind = clientRepository.findById(getClientId());

        if (this.ordersDone != null) {
            loyaltyCard.increaseOrdersDone();
        } else if (clientFind.isPresent()) {
            loyaltyCard.setClient(clientFind.get());
        }

        return loyaltyCard;
    }
}
