package br.com.projeto.ecantina.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "loyalty_card")
public class LoyaltyCard implements Serializable {

  private static final Long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Boolean valid;

  @Column(updatable = false, nullable = false)
  private LocalDate dateCreated;

  @Column(updatable = false, nullable = false)
  private LocalDate validThru;

  @Column(nullable = false)
  private Integer totalOrder;

  
  public LoyaltyCard() {}

  public LoyaltyCard(Boolean valid, LocalDate dateCreated, LocalDate validThru, Integer totalOrder) {
    this.valid = valid;
    this.dateCreated = dateCreated;
    this.validThru = validThru;
    this.totalOrder = totalOrder;
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
    LoyaltyCard other = (LoyaltyCard) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalDate getValidThru() {
    return validThru;
  }

  public void setValidThru(LocalDate validThru) {
    this.validThru = validThru;
  }

  public Integer getTotalOrder() {
    return totalOrder;
  }

  public void setTotalOrder(Integer totalOrder) {
    this.totalOrder = totalOrder;
  }
}
