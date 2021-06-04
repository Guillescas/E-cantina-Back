package br.com.projeto.ecantina.config.validation.validationclass;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projeto.ecantina.config.validation.notations.NameEquals;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;

public class EqualsNameValidator implements ConstraintValidator<NameEquals, String> {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        Optional<User> user = userRepository.findByName(value);

        return !user.isPresent();
    }
}
