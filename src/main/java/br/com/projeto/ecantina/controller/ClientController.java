package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.request.RequestClientDto;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity<ResponseClientDto> create(@RequestBody @Valid RequestClientDto requestClientDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Client client = requestClientDto.convertClient();
        clientRepository.save(client);
        URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseClientDto(client));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ResponseClientDto> detail(@PathVariable Long id) {
    //     Optional<Client> clientOptional = clientRepository.findById(id);

    //     if(clientOptional.isPresent()) {

    //         return ResponseEntity.ok(new );
    //     }
    // } 
}
