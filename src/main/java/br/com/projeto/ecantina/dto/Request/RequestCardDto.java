package br.com.projeto.ecantina.dto.request;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import br.com.projeto.ecantina.models.BankData;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.BankDataRepository;
import br.com.projeto.ecantina.repository.ClientRepository;

public class RequestCardDto {

    @NotBlank(message = "{cardNumber.blank}")
    @CreditCardNumber(message = "{cardNumber.format}")
    private String cardNumber;

    @Size(max = 50, message = "{owner.size}")
    @NotBlank(message = "{owner.blank}")
    private String owner;

    @NotBlank(message = "{validThru.blank}")
    private LocalDate validThru;

    @NotBlank(message = "{cpf.blank}")
    @Size(max = 14, message = "{cpf.size}")
    private String cpfClient;

    @Size(max = 3, min = 3, message = "{cvv.size}")
    @NotBlank(message = "{cvv.blank}")
    private String cvv;

    @NotBlank(message = "{bank.blank}")
    private String bank;

    public String getBank() {
        return bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public String getCvv() {
        return cvv;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getValidThru() {
        return validThru;
    }

    public Card convert(ClientRepository clientRepository, BankDataRepository bankDataRepository) {

        Optional<Client> clientFind = clientRepository.findByCpf(getCpfClient());
        Optional<BankData> bankData = bankDataRepository.findByName(getBank());

        if (clientFind.isPresent()) {

            if (bankData.isPresent())
                return new Card(cardNumber, owner, validThru, cvv, bankData.get());
            else {
                return new Card(cardNumber, owner, validThru, cvv, new BankData(getBank()));
            }
        } else {
            throw new NullPointerException("Cliente não encontrado");
        }
    }   
}
