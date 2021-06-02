package br.com.projeto.ecantina.dto.request.updatedto;

import br.com.projeto.ecantina.models.BankData;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.repository.BankDataRepository;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RequestUpdateCardDto {

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

    private BankData newBank;

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
        return LocalDate.parse(this.validThru, formatter);
    }

    public Card update(Optional<Card> cardFind, BankDataRepository bankDataRepository) {
        
        Card card = cardFind.get();
        Optional<BankData> bankFind = bankDataRepository.findByName(getBank());

        if(bankFind.isPresent()) {
            card.setBank(bankFind.get());
        } else {
            newBank = new BankData(getBank());
            card.setBank(newBank);
            bankDataRepository.save(newBank);
        }

        card.setCardNumber(getCardNumber());
        card.setCvv(getCvv());
        card.setOwner(getOwner());
        card.setValidThru(getValidThru());

        return card;
    }
}
