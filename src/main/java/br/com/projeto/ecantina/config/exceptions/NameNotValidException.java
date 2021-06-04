package br.com.projeto.ecantina.config.exceptions;

public class NameNotValidException extends RuntimeException {
    
    public NameNotValidException() {}

    public NameNotValidException(String msg) {
        super(msg);
    }
}
