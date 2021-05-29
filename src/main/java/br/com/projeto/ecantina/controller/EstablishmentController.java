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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestEstablishmentDto;
import br.com.projeto.ecantina.dto.request.updatedto.RequestUpdateEstablishmentDto;
import br.com.projeto.ecantina.dto.response.ResponseEstablishmentDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailEstablishmentDto;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.UserRepository;

@RestController
@RequestMapping("/establishment")
@CrossOrigin
public class EstablishmentController {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    UserRepository userRepository;

    @Value("Estabelecimento não encontrado")
    private String notFound;

    @GetMapping
    public Page<ResponseEstablishmentDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable) {

        Page<Establishment> allEstablishments = establishmentRepository.findAll(pageable);

        return ResponseEstablishmentDto.convert(allEstablishments);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEstablishmentDto> create(
            @RequestBody @Valid RequestEstablishmentDto requestEstablishmentDto,
            UriComponentsBuilder uriComponentsBuilder) {

        Establishment establishment = requestEstablishmentDto.convert();
        establishmentRepository.save(establishment);

        URI uri = uriComponentsBuilder.path("/establishment/{id}").buildAndExpand(establishment.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseEstablishmentDto(establishment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {

        Optional<Establishment> establishmentFind = establishmentRepository.findById(id);
        if (establishmentFind.isPresent()) {
            return ResponseEntity.ok(new ResponseDetailEstablishmentDto(establishmentFind.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody RequestUpdateEstablishmentDto requestUpdateEstablishmentDto) {

        Optional<Establishment> establishmentFind = establishmentRepository.findById(id);
        if (establishmentFind.isPresent()) {
            Establishment establishment = requestUpdateEstablishmentDto.update(establishmentFind, userRepository);
            establishmentRepository.save(establishment);
            return ResponseEntity.ok(new ResponseDetailEstablishmentDto(establishment));
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors(notFound, HttpStatus.NOT_FOUND.value()));
    }

}
