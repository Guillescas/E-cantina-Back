package br.com.projeto.ecantina.config.exceptions;

public class InvalidRatingException extends RuntimeException {
    
    public InvalidRatingException() {}

    public InvalidRatingException(String msg) {
        super(msg);
    }
}
