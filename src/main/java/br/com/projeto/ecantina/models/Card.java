package br.com.projeto.ecantina.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "card")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(length = 19, nullable = false)
  private String cardNumber;

  @Column(length = 50, nullable = false)
  private String owner;

  @Column(updatable = false, nullable = false)
  private LocalDate validThru;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(length = 3, nullable = false)
  private String cvv;

  @ManyToOne
  BankData bank;

  public Card() {}

  public Card(String cardNumber, String owner, LocalDate validThru, BigDecimal price, String cvv) {
    this.cardNumber = cardNumber;
    this.owner = owner;
    this.validThru = validThru;
    this.price = price;
    this.cvv = cvv;
  }

  public Card(String cardNumber, String owner, LocalDate validThru, BigDecimal price, String cvv, BankData bank) {
    this.cardNumber = cardNumber;
    this.owner = owner;
    this.validThru = validThru;
    this.price = price;
    this.cvv = cvv;
    this.bank = bank;
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
  
  public BankData getBank() {
      return bank;
  }

  public void setBank(BankData bank) {
      this.bank = bank;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }
}
