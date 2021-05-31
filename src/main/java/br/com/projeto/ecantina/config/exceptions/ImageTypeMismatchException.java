package br.com.projeto.ecantina.config.exceptions;

public class ImageTypeMismatchException extends RuntimeException {
    
    public ImageTypeMismatchException() {}

    public ImageTypeMismatchException(String msg) {
        super(msg);
    }
}
