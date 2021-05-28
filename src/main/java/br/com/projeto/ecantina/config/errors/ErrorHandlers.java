package br.com.projeto.ecantina.config.errors;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handle(Exception e, WebRequest request) {
        return new ResponseEntity<>("Acesso negado!", HttpStatus.FORBIDDEN);
    }
}
