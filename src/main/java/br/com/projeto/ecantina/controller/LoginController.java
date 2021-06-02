package br.com.projeto.ecantina.controller;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.config.security.TokenService;
import br.com.projeto.ecantina.dto.request.LoginDto;
import br.com.projeto.ecantina.dto.response.TokenDto;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Profile("prod")
@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class LoginController {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Object> valid() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody @Valid LoginDto loginDto) {

        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if(user.isPresent()) {

            UsernamePasswordAuthenticationToken dataLogin = loginDto.convert();
            Authentication authentication = authenticationManager.authenticate(dataLogin);
    
            String token = tokenService.generateToken(authentication);
    
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));

    }
}
