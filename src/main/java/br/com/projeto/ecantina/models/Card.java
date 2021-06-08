package br.com.projeto.ecantina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "card")
public class Card implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String nickname;

  @Column(length = 19, nullable = false)
  private String cardNumber;

  @Column(length = 50, nullable = false)
  private String owner;

  @Column(updatable = false, nullable = false)
  private LocalDate validThru;

  @Column(length = 3, nullable = false)
  private String cvv;

  @ManyToOne
  private BankData bank;

  public Card() {}

  public Card(String cardNumber, String owner, LocalDate validThru, String cvv) {
    this.cardNumber = cardNumber;
    this.owner = owner;
    this.validThru = validThru;
    this.cvv = cvv;
  }

  public Card(String cardNumber, String owner, LocalDate validThru, String cvv, String nickname,BankData bank) {
    this.cardNumber = cardNumber;
    this.owner = owner;
    this.validThru = validThru;
    this.cvv = cvv;
    this.bank = bank;
    this.nickname = nickname;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Card other = (Card) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public String getNickname() {
      return nickname;
  }

  public BankData getBank() {
      return bank;
  }

  public void setBank(BankData bank) {
      this.bank = bank;
  }

  public Long getId() {
    return id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public LocalDate getValidThru() {
    return validThru;
  }

  public void setValidThru(LocalDate validThru) {
    this.validThru = validThru;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }
}
