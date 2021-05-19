package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestOrderDto;
import br.com.projeto.ecantina.dto.response.ResponseOrderDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailOrderDto;
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

    @GetMapping
    public Page<ResponseOrderDto> list(@PageableDefault(sort = "createdAt") Pageable pageable, @RequestParam(required = false) Long clientId) {

        if(clientId != null) {
            Page<Order> orders = orderRepository.findByClient(clientId, pageable);
            return ResponseOrderDto.convert(orders);
        } else {
            // Pageable pageable = new Pageable()
            Page<Order> orders = orderRepository.findAll(pageable);
            return ResponseOrderDto.convert(orders);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseOrderDto> create(@RequestBody RequestOrderDto requestOrderDto, UriComponentsBuilder uriComponentsBuilder) {

        Order order = requestOrderDto.convert(clientRepository, restaurantRepository, productRepository);
        orderRepository.save(order);
        URI uri = uriComponentsBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseOrderDto(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> detail(@PathVariable Long orderId) {
        Optional<Order> orderFind = orderRepository.findById(orderId);

        if(orderFind.isPresent()) {
            return ResponseEntity.ok(new ResponseDetailOrderDto(orderFind.get()));
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Pedido não encontrado", HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/{orderId}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            orderRepository.delete(order.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Pedido não encontrado", HttpStatus.NOT_FOUND.value()));
    }
}
