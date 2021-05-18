package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<ResponseProductDto> list(@PageableDefault(sort = "type") Pageable pageable, @RequestParam(required = false) Long restaurantId) {
        
        if(restaurantId != null) {
            Page<Product> products = productRepository.findByRestaurantId(restaurantId, pageable);
            return ResponseProductDto.convert(products);
        } else {
            Page<Product> products = productRepository.findAll(pageable);
            return ResponseProductDto.convert(products);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> detail(@PathVariable Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return ResponseEntity.ok(new ResponseProductDto(product.get()));
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<ResponseProductDto> update(@PathVariable Long productId, @RequestBody RequestUpdateProductDto requestUpdateProductDto) {

        Optional<Product> productFind = productRepository.findById(productId);
        if(productFind.isPresent()) {
            Product product = requestUpdateProductDto.update(productId, productRepository);
            return ResponseEntity.ok(new ResponseProductDto(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> remove(@PathVariable Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
