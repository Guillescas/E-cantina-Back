package br.com.projeto.ecantina.controller;

import java.net.URI;

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

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class ImagesController {
    
    @Autowired
    private Image uploadImage;

    @PostMapping
    public ResponseEntity<ResponseImageDto> upload(@RequestParam MultipartFile image, UriComponentsBuilder uriComponentsBuilder) {
        uploadImage.saveImage(image);


        URI uri = uriComponentsBuilder.path("/upload/{originalFilename}").buildAndExpand(image.getOriginalFilename()).toUri();
        return ResponseEntity.created(uri).body(new ResponseImageDto(image.getOriginalFilename()));
    }
}
