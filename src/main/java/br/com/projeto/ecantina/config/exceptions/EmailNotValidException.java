package br.com.projeto.ecantina.config.exceptions;

public class EmailNotValidException extends RuntimeException {
    
    public EmailNotValidException() {

    }

    public EmailNotValidException(String msg) {
        super(msg);
    }

}
