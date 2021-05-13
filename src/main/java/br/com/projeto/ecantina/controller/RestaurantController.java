package br.com.projeto.ecantina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.ecantina.dto.response.ResponseRestaurantDto;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @GetMapping
    public Page<ResponseRestaurantDto> list(@RequestParam(required = false) String nameRestaurant,
            @RequestParam(required = false) String nameEstablishment,
            @PageableDefault(sort = "id", direction = Direction.ASC, size = 10) Pageable pageable) {

        if (nameEstablishment != null) {
            Establishment establishment = establishmentRepository.findByName(nameEstablishment);

            Page<Restaurant> restaurants = restaurantRepository.findEstablishmentRestaurants(establishment.getId(), pageable);
            return ResponseRestaurantDto.convert(restaurants);
        }

        Page<Restaurant> allRestaurants = restaurantRepository.findAll(pageable);
        return ResponseRestaurantDto.convert(allRestaurants);
    }
}
