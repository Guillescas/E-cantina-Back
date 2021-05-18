package br.com.projeto.ecantina.config.validation.validationclass;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projeto.ecantina.config.validation.notations.EmailEquals;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;

public class EqualsEmailValidator implements ConstraintValidator<EmailEquals, String>{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        Optional<User> user = userRepository.findByEmail(value);

        return !user.isPresent();
    }
    
}
