package br.com.projeto.ecantina.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;

@RestControllerAdvice
public class ErrorValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public List<ResponseRegisterError> handle(MethodArgumentNotValidException exception) {
        List<ResponseRegisterError> rreDto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ResponseRegisterError error = new ResponseRegisterError(e.getField(), message);
            rreDto.add(error);
        });

        return rreDto;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = EmailNotValidException.class)
    public ResponseRegisterError handle(EmailNotValidException exception) {
        ResponseRegisterError rreDto = new ResponseRegisterError("Email", exception.getMessage());

        return rreDto;
    }
}
