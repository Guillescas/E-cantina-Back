package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.components.ImageComponent;
import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.response.ResponseClientDto;
import br.com.projeto.ecantina.dto.response.ResponseEstablishmentDto;
import br.com.projeto.ecantina.dto.response.ResponseProductDto;
import br.com.projeto.ecantina.dto.response.ResponseRestaurantDto;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.ProductRepository;
import br.com.projeto.ecantina.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class ImagesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageComponent imageComponent;

    @Transactional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> upload(@RequestPart MultipartFile image, @RequestParam Long userId,
            @RequestParam(required = false) Long productId, UriComponentsBuilder uriComponentsBuilder) {

        Product product = null;
        Optional<User> userFind = userRepository.findById(userId);

        if (productId != null) {
            Optional<Product> productFind = productRepository.findById(productId);
            product = productFind.get();
        }

        if (userFind.isPresent()) {
            String fileName = imageComponent.saveImage(image, userFind.get(), product);
            URI uri = uriComponentsBuilder.path(fileName).build().toUri();

            if (product != null) {
                product.setUrlImage(fileName);
                productRepository.save(product);
                return ResponseEntity.created(uri).body(new ResponseProductDto(product));
            } else {
                userFind.get().setUrlImage(fileName);
                userRepository.save(userFind.get());

                if (userFind.get().getType().equals("client"))
                    return ResponseEntity.created(uri).body(new ResponseClientDto((Client) userFind.get()));
                else if (userFind.get().getType().equals("restaurant"))
                    return ResponseEntity.created(uri).body(new ResponseRestaurantDto((Restaurant) userFind.get()));
                else if (userFind.get().getType().equals("establishment"))
                    return ResponseEntity.created(uri)
                            .body(new ResponseEstablishmentDto((Establishment) userFind.get()));
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrors("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));
    }
}
