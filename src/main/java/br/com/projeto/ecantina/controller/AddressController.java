package br.com.projeto.ecantina.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.ecantina.dto.request.RequestAddressDto;
import br.com.projeto.ecantina.dto.response.ResponseAddressDto;
import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.repository.AddressRepository;
import br.com.projeto.ecantina.repository.ClientRepository;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAddressDto> create(@RequestBody @Valid RequestAddressDto requestAddressDto, UriComponentsBuilder uriComponentsBuilder) {

        Address address = requestAddressDto.convert(clientRepository);
        addressRepository.save(address);
        URI uri = uriComponentsBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new ResponseAddressDto(address));
    }
}
