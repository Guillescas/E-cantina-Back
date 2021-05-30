package br.com.projeto.ecantina.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Rating implements Serializable {

    private static final long serialVersionUID = 42L;
    
    private Long id;

    private BigDecimal value;

    private String description;
}
