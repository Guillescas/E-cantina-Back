package br.com.projeto.ecantina.config.errors;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlers {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseErrors handle(NullPointerException exception) {

        String msg = exception.toString();
        if(msg.contains(":")) {
            String[] error = msg.split(":");
            return new ResponseErrors(error[0], error[1]);
        } else {
            return new ResponseErrors("Error", msg);
        }

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseErrors handle(InvalidDataAccessResourceUsageException exception) {

        String msg = exception.getMessage();
        exception.printStackTrace();

        return new ResponseErrors("Error", msg);
    }
}
