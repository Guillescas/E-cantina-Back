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

import br.com.projeto.ecantina.dto.request.RequestClientDto;
import br.com.projeto.ecantina.dto.request.RequestEstablishmentDto;
import br.com.projeto.ecantina.dto.request.RequestRestaurantDto;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.dto.response.ResponseEstablishmentDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;;
    
    @PostMapping("/establishment")
    @Transactional
    public ResponseEntity<ResponseEstablishmentDto> create(
            @RequestBody RequestEstablishmentDto requestEstablishmentDto, UriComponentsBuilder uriComponentsBuilder) {

                Establishment establishment = requestEstablishmentDto.convert();
                establishmentRepository.save(establishment);

                URI uri = uriComponentsBuilder.path("register/{id}").buildAndExpand(establishment.getId()).toUri();
                return ResponseEntity.created(uri).body(new ResponseEstablishmentDto(establishment));
    }

    @PostMapping("/client")
    @Transactional
    public ResponseEntity<ResponseClientDto> create(@RequestBody RequestClientDto requestUserDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Client client = (Client) requestUserDto.convertClient();
        clientRepository.save(client);
        URI uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseClientDto(client));
    }

    @PostMapping("/restaurant")
    @Transactional
    public ResponseEntity<ResponseRestaurantDto> create(@RequestBody RequestRestaurantDto requestRestaurantDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Restaurant restaurant = requestRestaurantDto.convert(establishmentRepository);
        restaurantRepository.save(restaurant);

        URI uri = uriComponentsBuilder.path("/cadastro").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseRestaurantDto(restaurant));
    }
}
