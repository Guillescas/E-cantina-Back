package br.com.projeto.ecantina.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.components.ImageComponent;
import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.response.ResponseImageDto;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class ImagesController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ImageComponent uploadImage;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> upload(@RequestParam MultipartFile image, @RequestParam Long userId,UriComponentsBuilder uriComponentsBuilder) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            String fileName = uploadImage.saveImage(image, user.get());
    
            URI uri = uriComponentsBuilder.path(fileName).build().toUri();
            return ResponseEntity.created(uri).body(new ResponseImageDto(image.getOriginalFilename()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));

    }
}
