package br.com.projeto.ecantina.config.exceptions;

public class InvalidLoyaltyCardException extends RuntimeException {
    
    public InvalidLoyaltyCardException() {}

    public InvalidLoyaltyCardException (String msg) {
        super(msg);
    }
}
