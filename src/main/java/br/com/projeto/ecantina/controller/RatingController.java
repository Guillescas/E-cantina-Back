package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestRatingDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateRating;
import br.com.projeto.ecantina.dto.response.ResponseRatingDto;
import br.com.projeto.ecantina.models.Rating;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.RatingRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Value("Avaliação não encontrada")
    private String notFound;

    @PostMapping
    public ResponseEntity<ResponseRatingDto> create(@RequestBody @Valid RequestRatingDto requestRatingDto, UriComponentsBuilder uriComponentsBuilder) {
        
        Rating rating = requestRatingDto.convert(ratingRepository, clientRepository, restaurantRepository);
        ratingRepository.save(rating);

        URI uri = uriComponentsBuilder.path("/rating/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseRatingDto(rating));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<Rating> ratingFind = ratingRepository.findById(id);
        if (ratingFind.isPresent()) {
            return ResponseEntity.ok(new ResponseRatingDto(ratingFind.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody RequestUpdateRating requestUpdateRating) {

        Optional<Rating> ratingFind = ratingRepository.findById(id);
        if (ratingFind.isPresent()) {
            Rating rating = requestUpdateRating.convert(ratingFind.get());
            ratingRepository.save(rating);
            return ResponseEntity.ok(new ResponseRatingDto(rating));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<Rating> ratingFind = ratingRepository.findById(id);
        if (ratingFind.isPresent()) {
            ratingRepository.delete(ratingFind.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}
