package br.com.projeto.ecantina.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.request.RequestUserDto;
import br.com.projeto.ecantina.dto.response.ResponseUserDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.TypeUser;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class RegisterController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // @GetMapping 
    // public Page<ResponseUserDto> list(@RequestParam(required = false) String nameRestaurant, @RequestParam(required = false) String nameEstablishment, @PageableDefault(sort = "rating", direction = Direction.ASC, size = 10) Pageable pageable,
    //         @RequestParam(required = false) String typeUser) {

    //     if (typeUser.equals(TypeUser.Client.toString())) {

    //         Page<Restaurant> allRestaurants = restaurantRepository.findAll(pageable);
    //         return ResponseUserDto.convert(allRestaurants);
    //     } else {

    //         Page<Establishment>
    //     }
    // }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUserDto> register(@RequestBody RequestUserDto requestUserDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Client client = (Client) requestUserDto.convertClient();
        clientRepository.save(client);
        URI uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseUserDto(client));
    }
}
