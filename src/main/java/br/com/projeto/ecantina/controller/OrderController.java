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

import br.com.projeto.ecantina.dto.request.RequestOrderDto;
import br.com.projeto.ecantina.dto.response.ResponseOrderDto;
import br.com.projeto.ecantina.models.Order;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.OrderRepository;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    OrderRepository orderRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseOrderDto> create(@RequestBody RequestOrderDto requestOrderDto, UriComponentsBuilder uriComponentsBuilder) {

        Order order = requestOrderDto.convert(clientRepository, restaurantRepository, productRepository);
        orderRepository.save(order);
        URI uri = uriComponentsBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseOrderDto(order));
    }
}
