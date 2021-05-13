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

import br.com.projeto.ecantina.dto.request.RequestEstablishmentDto;
import br.com.projeto.ecantina.dto.response.ResponseEstablishmentDto;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

@RestController
@RequestMapping("/establishment")
@CrossOrigin
public class EstablishmentController {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEstablishmentDto> create(
            @RequestBody RequestEstablishmentDto requestEstablishmentDto, UriComponentsBuilder uriComponentsBuilder) {

                Establishment establishment = requestEstablishmentDto.convert();
                establishmentRepository.save(establishment);

                URI uri = uriComponentsBuilder.path("register/{id}").buildAndExpand(establishment.getId()).toUri();
                return ResponseEntity.created(uri).body(new ResponseEstablishmentDto(establishment));
    }
}
