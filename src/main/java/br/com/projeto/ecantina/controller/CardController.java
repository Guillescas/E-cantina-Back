package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestCardDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateCardDto;
import br.com.projeto.ecantina.dto.response.ResponseCardDto;
import br.com.projeto.ecantina.models.Card;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.repository.BankDataRepository;
import br.com.projeto.ecantina.repository.CardRepository;
import br.com.projeto.ecantina.repository.ClientRepository;

@RestController
@CrossOrigin
@RequestMapping("/card")
public class CardController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BankDataRepository bankDataRepository;

    @Value("Cartão não encontrado")
    private String notFound;

    @GetMapping
    public Page<Card> list(@PageableDefault(sort = "id", direction = Direction.ASC, size = 10) Pageable pageable,
            @RequestParam Long clientId) {

        Optional<Client> cOptional = clientRepository.findById(clientId);

        if (cOptional.isPresent()) {
            return cardRepository.findByClient(clientId, pageable);
        }

        throw new NullPointerException("Cliente não encontrado");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@Valid @RequestBody RequestCardDto requestCardDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Card card = requestCardDto.convert(clientRepository, bankDataRepository);
        cardRepository.save(card);

        URI uri = uriComponentsBuilder.path("/card/{id}").buildAndExpand(card.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCardDto(card));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<Card> cardFind = cardRepository.findById(id);
        if (cardFind.isPresent()) {
            return ResponseEntity.ok(new ResponseCardDto(cardFind.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    // @PatchMapping("/{id}")
    // @Transactional
    // public ResponseEntity<Object> update(@RequestBody RequestUpdateCardDto requestUpdateCardDto, @PathVariable Long id) {

    //     Optional
    // }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<Card> cardFind = cardRepository.findById(id);
        if (cardFind.isPresent()) {
             cardRepository.delete(cardFind.get());
             return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}