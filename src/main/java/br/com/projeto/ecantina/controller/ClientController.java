package br.com.projeto.ecantina.controller;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestClientDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateClientDto;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailClientDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Value("Cliente não encontrado")
    private String notFound;

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
                    .body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long id,
            @RequestBody RequestUpdateClientDto requestUpdateClientDto) {

        Optional<Client> clientFind = clientRepository.findById(id);
        if (clientFind.isPresent()) {
            Client client = requestUpdateClientDto.update(clientFind, userRepository);
            return ResponseEntity.ok(new ResponseDetailClientDto(client));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}
