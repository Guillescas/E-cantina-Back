package br.com.projeto.ecantina.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import br.com.projeto.ecantina.dto.request.RequestRestaurantDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantDto;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.CategoryRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired CategoryRepository categoryRepository;

    @GetMapping
    public Page<ResponseRestaurantDto> list(@RequestParam(required = false) String nameRestaurant,
            @RequestParam(required = false) String nameEstablishment,
            @PageableDefault(sort = "id", direction = Direction.ASC, size = 10) Pageable pageable) {

        Page<Restaurant> restaurants = null;

        if (nameEstablishment != null) {
            var establishment = establishmentRepository.findByName(nameEstablishment);

            if (nameRestaurant != null) {
                restaurants = restaurantRepository.findEstablishmentRestaurantsPerName(establishment.getId(),
                        nameRestaurant, pageable);
            } else {
                restaurants = restaurantRepository.findEstablishmentRestaurants(establishment.getId(), pageable);
            }

            return ResponseRestaurantDto.convert(restaurants);
        }

        Page<Restaurant> allRestaurants = restaurantRepository.findAll(pageable);
        return ResponseRestaurantDto.convert(allRestaurants);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseRestaurantDto> create(@RequestBody @Valid RequestRestaurantDto requestRestaurantDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Restaurant restaurant = requestRestaurantDto.convert(establishmentRepository, categoryRepository);
        restaurantRepository.save(restaurant);

        URI uri = uriComponentsBuilder.path("/restaurant/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseRestaurantDto(restaurant));
    }
}
