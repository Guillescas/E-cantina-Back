package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestDiscountCouponDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateDiscountCouponDto;
import br.com.projeto.ecantina.dto.response.ResponseDiscountCouponDto;
import br.com.projeto.ecantina.models.DiscountCoupon;
import br.com.projeto.ecantina.repository.DiscountCouponRepository;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@CrossOrigin
@RequestMapping("/discount")
public class DiscountCouponController {

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Value("O cupom de desconto não foi encontrado")
    private String notFound;

    @GetMapping
    public List<ResponseDiscountCouponDto> list(@RequestParam(required = false) String restaurantName) {

        if (restaurantName == null) {
            List<DiscountCoupon> allDiscountCoupon = discountCouponRepository.findByRestaurantName(restaurantName);
            return ResponseDiscountCouponDto.convert(allDiscountCoupon);
        }

        List<DiscountCoupon> allDiscountCoupon = discountCouponRepository.findAll();
        return ResponseDiscountCouponDto.convert(allDiscountCoupon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<DiscountCoupon> discountFind = discountCouponRepository.findById(id);
        if (discountFind.isPresent()) {
            return ResponseEntity.ok(new ResponseDiscountCouponDto(discountFind.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Cupom de desconto não encontrado", HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestDiscountCouponDto requestDiscountCouponDto, UriComponentsBuilder uriComponentsBuilder) {

        DiscountCoupon discountCoupon = requestDiscountCouponDto.convert(restaurantRepository);
        discountCouponRepository.save(discountCoupon);

        URI uri = uriComponentsBuilder.path("/discount/{id}").buildAndExpand(discountCoupon.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDiscountCouponDto(discountCoupon));
    }

    @PatchMapping("/{id}") // TODO Patch cupom de desconto
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody RequestUpdateDiscountCouponDto requestUpdateDiscountCouponDto) {
        
        Optional<DiscountCoupon> discountCouponFind = discountCouponRepository.findById(id);
        if (discountCouponFind.isPresent()) {
            DiscountCoupon discountCoupon = requestUpdateDiscountCouponDto.update(discountCouponFind.get(), productRepository);
            discountCouponRepository.save(discountCoupon);
            return ResponseEntity.ok(new ResponseDiscountCouponDto(discountCoupon));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        Optional<DiscountCoupon> discountFind = discountCouponRepository.findById(id);
        if (discountFind.isPresent()) {
            discountCouponRepository.delete(discountFind.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }
}
