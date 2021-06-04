package br.com.projeto.ecantina.config.validation.notations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.projeto.ecantina.config.validation.validationclass.EqualsNameValidator;

@Constraint(validatedBy = EqualsNameValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface NameEquals {
    
    public String message() default "O nome ja está em uso";

    public Class<?>[] groups() default{};

    public Class<?extends Payload>[]payload() default{};
}
