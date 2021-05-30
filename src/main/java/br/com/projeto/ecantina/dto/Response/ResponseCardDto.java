package br.com.projeto.ecantina.dto.response;

import java.time.LocalDate;

import br.com.projeto.ecantina.models.BankData;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;

public class ResponseCardDto {

    private Long id;

    private String cardNumber;

    private String owner;

    private LocalDate validThru;

    private Client client;

    private String cvv;

    private BankData bank;

    public ResponseCardDto(Card card) {
        this.id = card.getId();
        this.cardNumber = card.getCardNumber();
        this.owner = card.getOwner();
        this.validThru = card.getValidThru();
        this.client = card.getClient();
        this.cvv = card.getCvv();
        this.bank = card.getBank();
    }

    public BankData getBank() {
        return bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Client getClient() {
        return client;
    }

    public String getCvv() {
        return cvv;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getValidThru() {
        return validThru;
    }
}
