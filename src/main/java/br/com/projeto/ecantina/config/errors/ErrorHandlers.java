package br.com.projeto.ecantina.config.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.projeto.ecantina.config.validation.ResponseRegisterError;

@RestControllerAdvice
public class ErrorHandlers {

    // TODO InvalidDataAccessResourceUsageException handler
    
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseRegisterError handle(NullPointerException exception) {

        String msg = exception.getMessage();
        if(msg.contains(":")) {
            String[] error = msg.split(":");
            return new ResponseRegisterError(error[0], error[1]);
        } else {
            return new ResponseRegisterError("Error", msg);
        }

    }
}
