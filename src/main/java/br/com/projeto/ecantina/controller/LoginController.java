package br.com.projeto.ecantina.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.ecantina.config.security.TokenService;
import br.com.projeto.ecantina.dto.request.LoginDto;
import br.com.projeto.ecantina.dto.response.TokenDto;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginDto loginDto) {

        try {

            UsernamePasswordAuthenticationToken dataLogin = loginDto.convert();
            Authentication authentication = authenticationManager.authenticate(dataLogin);

            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch(AuthenticationException ex) {

            return ResponseEntity.badRequest().build();
        }
    }
}
