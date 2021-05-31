package br.com.projeto.ecantina.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.response.ResponseImageDto;
import br.com.projeto.ecantina.models.storage.Image;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.ImageStorageRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;
import br.com.projeto.ecantina.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class ImagesController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Image uploadImage;

    @Autowired
    ImageStorageRepository imageStorageRepository;

    @PostMapping
    @Transactional // TODO insert a path variable to identify the user that is uploading.
    public ResponseEntity<ResponseImageDto> upload(@RequestParam MultipartFile image, UriComponentsBuilder uriComponentsBuilder) {

        // TODO insert the user id here.
        uploadImage.saveImage(image, 01L, imageStorageRepository);


        URI uri = uriComponentsBuilder.path("/upload/{originalFilename}").buildAndExpand(image.getOriginalFilename()).toUri();
        return ResponseEntity.created(uri).body(new ResponseImageDto(image.getOriginalFilename()));
    }
}
