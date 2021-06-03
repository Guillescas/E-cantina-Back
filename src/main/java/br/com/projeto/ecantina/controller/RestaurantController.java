package br.com.projeto.ecantina.controller;

import br.com.projeto.ecantina.config.components.ImageComponent;
import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestRestaurantDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateRestaurantDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailRestaurantDto;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.CategoryRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;
import br.com.projeto.ecantina.repository.UserRepository;
import br.com.projeto.ecantina.specification.SpecificationRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageComponent imageComponent;

    @Value("Restaurante não encontrado")
    private String notFound;

    @GetMapping
    public Page<ResponseRestaurantDto> list(@RequestParam(required = false) String nameRestaurant,
            @RequestParam(required = false) String nameEstablishment,
            @RequestParam(required = false) String nameCategory,
            @PageableDefault(sort = "id", direction = Direction.ASC, size = 10) Pageable pageable) {

        Establishment establishment = null;
        Category category = null;
        Optional<Category> categoryOptional = categoryRepository.findByName(nameCategory);
        Optional<Establishment> establishmentOptional = establishmentRepository.findByName(nameEstablishment);

        if (establishmentOptional.isPresent()) {
            establishment = establishmentOptional.get();
        }
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
        }

        Page<Restaurant> allRestaurants = restaurantRepository
                .findAll(Specification.where(SpecificationRestaurant.restaurantName(nameRestaurant))
                        .or(SpecificationRestaurant.establishment(establishment))
                        .or(SpecificationRestaurant.categories(category)), pageable);
        return ResponseRestaurantDto.convert(allRestaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {

            return ResponseEntity.ok(new ResponseDetailRestaurantDto(restaurant.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
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

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long id,
            @Valid @RequestBody RequestUpdateRestaurantDto requestUpdateRestaurantDto) {

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(id);

        if (restaurantFind.isPresent()) {
            Restaurant restaurant = requestUpdateRestaurantDto.update(restaurantFind, userRepository,
                    categoryRepository);
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok(new ResponseDetailRestaurantDto(restaurant));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long id) {

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(id);

        if (restaurantFind.isPresent()) {
            imageComponent.deleteImageUser(restaurantFind.get());
            restaurantFind.get().getProducts().forEach(product -> {
                imageComponent.deleteImageProduct(product);
            });
            restaurantRepository.delete(restaurantFind.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}
