package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
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
import br.com.projeto.ecantina.dto.request.RequestLoyaltyCardDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateLoyaltyCardDto;
import br.com.projeto.ecantina.dto.response.ResponseLoyaltyCardDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.LoyaltyCardRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;
import br.com.projeto.ecantina.specification.SpecificationLoyaltyCard;

@RestController
@CrossOrigin
@RequestMapping("/loyaltycard")
public class LoyaltyCardController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    @Value("Cartão de lealdade não encontrado")
    private String notFound;
    
    @PostMapping
    public ResponseEntity<ResponseLoyaltyCardDto> create(@RequestBody RequestLoyaltyCardDto requestLoyaltyCard, UriComponentsBuilder uriComponentsBuilder) {

        LoyaltyCard loyaltyCard = requestLoyaltyCard.convert(restaurantRepository);

        URI uri = uriComponentsBuilder.path("/loyaltycard/{id}").buildAndExpand(loyaltyCard.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseLoyaltyCardDto(loyaltyCard));
    }

    @GetMapping
    public List<ResponseLoyaltyCardDto> list(@RequestParam(required = false) Long restaurantId, @RequestParam(required = false) Long clientId) {

        Restaurant restaurant = null;
        Client client = null;

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(restaurantId);
        Optional<Client> clientFind = clientRepository.findById(clientId);

        if (restaurantFind.isPresent()) {
            restaurant = restaurantFind.get();
        } else if (clientFind.isPresent()) {
            client = clientFind.get();
        }
        
        List<LoyaltyCard> loyaltyCards = loyaltyCardRepository.findAll(Specification
            .where(SpecificationLoyaltyCard.restaurant(restaurant))
            .or(SpecificationLoyaltyCard.client(client)));

        return ResponseLoyaltyCardDto.convert(loyaltyCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<LoyaltyCard> loyaltyCardFind = loyaltyCardRepository.findById(id);
        if (loyaltyCardFind.isPresent()) {
            if (Boolean.TRUE.equals(loyaltyCardFind.get().getValid())) {
                return ResponseEntity.ok(new ResponseLoyaltyCardDto(loyaltyCardFind.get()));
            }

            return ResponseEntity.badRequest().body(new ResponseErrors("Cartão de lealdade vencido", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update (@PathVariable Long id, @RequestBody RequestUpdateLoyaltyCardDto requestUpdateLoyaltyCardDto) {

        Optional<LoyaltyCard> loyaltyCardFind = loyaltyCardRepository.findById(id);
        if (loyaltyCardFind.isPresent()) {
            LoyaltyCard loyaltyCard = requestUpdateLoyaltyCardDto.convert(loyaltyCardFind.get(), clientRepository);
            loyaltyCardRepository.save(loyaltyCard);
            return ResponseEntity.ok(new ResponseLoyaltyCardDto(loyaltyCard));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<LoyaltyCard> loyaltyCardFind = loyaltyCardRepository.findById(id);
        if (loyaltyCardFind.isPresent()) {
            loyaltyCardRepository.delete(loyaltyCardFind.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}
