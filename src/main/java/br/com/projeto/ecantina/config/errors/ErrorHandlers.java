package br.com.projeto.ecantina.config.errors;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;

@RestControllerAdvice
public class ErrorHandlers {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseErrors handle(NullPointerException exception) {

        String msg = exception.getMessage();
        exception.printStackTrace();

        return new ResponseErrors(msg, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseErrors handle(InvalidDataAccessResourceUsageException exception) {

        String msg = exception.getMessage();
        exception.printStackTrace();

        return new ResponseErrors(msg, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotValidException.class)
    public ResponseErrors handle(EmailNotValidException exception, WebRequest request) {

        String msg = exception.getMessage();
        exception.printStackTrace();


        return new ResponseErrors(msg, HttpStatus.BAD_REQUEST.value());
    }
}
