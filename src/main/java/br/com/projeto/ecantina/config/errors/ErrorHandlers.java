package br.com.projeto.ecantina.config.errors;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseErrors handle(AuthenticationException exception) {

        String msg = exception.getLocalizedMessage();
        exception.printStackTrace();

        return new ResponseErrors(msg, HttpStatus.BAD_REQUEST.value());
    }
}
