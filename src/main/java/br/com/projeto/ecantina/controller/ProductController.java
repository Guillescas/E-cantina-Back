package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.projeto.ecantina.dto.request.RequestProductDto;
import br.com.projeto.ecantina.dto.request.RequestUpdateProductDto;
import br.com.projeto.ecantina.dto.response.ResponseProductDto;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ProductRepository productRepository;

    @Value("Produto não encontrado")
    private String notFound;

    @GetMapping
    public Page<ResponseProductDto> list(@PageableDefault(sort = "type") Pageable pageable,
            @RequestParam(required = false) Long restaurantId) {

        if (restaurantId != null) {
            Page<Product> products = productRepository.findByRestaurantId(restaurantId, pageable);
            return ResponseProductDto.convert(products);
        } else {
            Page<Product> products = productRepository.findAll(pageable);
            return ResponseProductDto.convert(products);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(new ResponseProductDto(product.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseProductDto> create(@RequestBody @Valid RequestProductDto requestProductDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Product product = requestProductDto.convert(restaurantRepository);

        productRepository.save(product);
        URI uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseProductDto(product));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long id,
            @RequestBody RequestUpdateProductDto requestUpdateProductDto) {

        Optional<Product> productFind = productRepository.findById(id);
        if (productFind.isPresent()) {
            Product product = requestUpdateProductDto.update(id, productRepository);
            return ResponseEntity.ok(new ResponseProductDto(product));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> remove(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));

    }
}
