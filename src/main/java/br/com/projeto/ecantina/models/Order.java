package br.com.projeto.ecantina.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Boolean finished = false;

    @Column(updatable = false, nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(updatable = false, nullable = false)
    private LocalDate finishAt = LocalDate.now().plusDays(2);

    @Column(length = 300)
    private String observation;

    @Column(nullable = false)
    private Boolean valid = true;

    public Order() {}

    public Order(String observation) {
        this.observation = observation;
    }
    
}
