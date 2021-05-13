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

import br.com.projeto.ecantina.dto.request.RequestProductDto;
import br.com.projeto.ecantina.dto.response.ResponseProductDto;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProductController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseProductDto> create(@RequestBody RequestProductDto requestProductDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Product product = requestProductDto.convert(restaurantRepository);

        productRepository.save(product);
        URI uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseProductDto(product));
    }
}
