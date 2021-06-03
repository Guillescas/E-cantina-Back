package br.com.projeto.ecantina.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.request.RequestLoyaltyCardDto;
import br.com.projeto.ecantina.dto.response.ResponseLoyaltyCardDto;
import br.com.projeto.ecantina.models.LoyaltyCard;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@CrossOrigin
@RequestMapping("/loyaltycard")
public class LoyaltyCardController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    @PostMapping
    public ResponseEntity<ResponseLoyaltyCardDto> create(@RequestBody RequestLoyaltyCardDto requestLoyaltyCard, UriComponentsBuilder uriComponentsBuilder) {

        LoyaltyCard loyaltyCard = requestLoyaltyCard.convert(restaurantRepository);

        URI uri = uriComponentsBuilder.path("/loyaltycard/{id}").buildAndExpand(loyaltyCard.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseLoyaltyCardDto(loyaltyCard));
    }

    // @GetMapping
    // public List<ResponseLoyaltyCardDto>
}
