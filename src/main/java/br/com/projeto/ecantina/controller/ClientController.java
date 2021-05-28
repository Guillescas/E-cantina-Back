package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestClientDto;
import br.com.projeto.ecantina.dto.request.RequestUpdateClientDto;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailClientDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.UserRepository;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClientDto> create(@RequestBody @Valid RequestClientDto requestClientDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Client client = requestClientDto.convertClient();
        clientRepository.save(client);
        URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseClientDto(client));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(new ResponseDetailClientDto(client.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseErrors("Cliente não encontrado", HttpStatus.NOT_FOUND.value()));
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long clientId,
            @RequestBody RequestUpdateClientDto requestUpdateClientDto) {

        Optional<Client> clientFind = clientRepository.findById(clientId);
        if (clientFind.isPresent()) {
            Client client = requestUpdateClientDto.update(clientId, clientRepository, userRepository);
            return ResponseEntity.ok(new ResponseDetailClientDto(client));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Cliente não encontrado", HttpStatus.NOT_FOUND.value()));
    }
}
