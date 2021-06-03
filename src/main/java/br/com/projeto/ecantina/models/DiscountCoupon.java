package br.com.projeto.ecantina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name="discount_coupon")
public class DiscountCoupon implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 6, nullable = false)
  private String code;

  @Column(nullable = false)
  private BigDecimal value;

  @Column(nullable = false)
  private Boolean used = false;

  @Column(updatable = false, nullable = false)
  private LocalDate finishedAt;

  @OneToMany
  @JoinColumn(name = "discount_coupon_id")
  private List<Product> products;

  public DiscountCoupon() {}

  public DiscountCoupon(String code, BigDecimal value, LocalDate finishedAt, List<Product> products) {
    this.code = code;
    this.value = value;
    this.finishedAt = finishedAt;
    this.products = products;
  }

  public String getCode() {
      return code;
  }

  public Boolean getUsed() {
      return used;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    DiscountCoupon other = (DiscountCoupon) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public List<Product> getProducts() {
      return products;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public LocalDate getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(LocalDate finishedAt) {
    this.finishedAt = finishedAt;
  }
}

