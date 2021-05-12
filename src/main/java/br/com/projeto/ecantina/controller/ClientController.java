package br.com.projeto.ecantina.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.request.RequestUserDto;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;

@RestController
@RequestMapping("/usuario/cliente")
@CrossOrigin
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClientDto> register(@RequestBody RequestUserDto requestUserDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Client client = (Client) requestUserDto.convertClient();
        clientRepository.save(client);
        URI uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseClientDto(client));
    }
}
