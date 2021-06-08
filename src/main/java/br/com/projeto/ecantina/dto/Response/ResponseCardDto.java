package br.com.projeto.ecantina.dto.response;

import br.com.projeto.ecantina.models.BankData;
import br.com.projeto.ecantina.models.Card;

import java.time.LocalDate;

public class ResponseCardDto {

    private Long id;

    private String cardNumber;

    private String owner;

    private LocalDate validThru;

    private String cvv;

    private String nickname;

    private BankData bank;

    public ResponseCardDto(Card card) {
        this.id = card.getId();
        this.cardNumber = card.getCardNumber();
        this.owner = card.getOwner();
        this.validThru = card.getValidThru();
        this.cvv = card.getCvv();
        this.bank = card.getBank();
        this.nickname = card.getNickname();
    }

    public String getNickname() {
        return nickname;
    }

    public BankData getBank() {
        return bank;
    }

    public String getCardNumber() {
        return cardNumber;
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
