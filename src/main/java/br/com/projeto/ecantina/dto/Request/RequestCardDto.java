package br.com.projeto.ecantina.dto.request;

import br.com.projeto.ecantina.models.BankData;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.BankDataRepository;
import br.com.projeto.ecantina.repository.ClientRepository;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RequestCardDto {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NotBlank(message = "{cardNumber.blank}")
    @CreditCardNumber(message = "{cardNumber.format}")
    private String cardNumber;

    @Size(max = 50, message = "{owner.size}")
    @NotBlank(message = "{owner.blank}")
    private String owner;

    @NotBlank(message = "{validThru.blank}")
    private String validThru;

    @NotBlank(message = "{cpf.blank}")
    @Size(max = 14, message = "{cpf.size}")
    private String cpfClient;

    @Size(max = 3, min = 3, message = "{cvv.size}")
    @NotBlank(message = "{cvv.blank}")
    private String cvv;

    @NotBlank(message = "{bank.blank}")
    private String bank;

    public String getBank() {
        return bank.toUpperCase();
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
        return LocalDate.parse(this.validThru, formatter);
    }

    public Card convert(ClientRepository clientRepository, BankDataRepository bankDataRepository) {

        Optional<Client> clientFind = clientRepository.findByCpf(getCpfClient());
        Optional<BankData> bankData = bankDataRepository.findByName(getBank());

        if (clientFind.isPresent()) {
            if (bankData.isPresent()) {
                Card card = new Card(getCardNumber(), getOwner(), getValidThru(), getCvv(), bankData.get());
                clientFind.get().getCards().add(card);
                return card;
            } else {
                BankData newBank = new BankData(getBank());
                bankDataRepository.save(newBank);
                Card card = new Card(getCardNumber(), getOwner(), getValidThru(), getCvv(), newBank);
                clientFind.get().getCards().add(card);
                return card;
            }
        } else {
            throw new NullPointerException("Cliente não encontrado");
        }
    }
}
