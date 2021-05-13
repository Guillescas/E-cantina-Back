package br.com.projeto.ecantina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.ecantina.repository.ClientRepository;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;

}
