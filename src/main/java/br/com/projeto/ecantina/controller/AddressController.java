package br.com.projeto.ecantina.controller;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import br.com.projeto.ecantina.dto.request.RequestAddressDto;
import br.com.projeto.ecantina.dto.response.ResponseAddressDto;
import br.com.projeto.ecantina.dto.response.detailresponse.ResponseDetailAddressDto;
import br.com.projeto.ecantina.models.Address;
import br.com.projeto.ecantina.repository.AddressRepository;
import br.com.projeto.ecantina.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public Page<ResponseAddressDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable) {

        Page<Address> allAddress = addressRepository.findAll(pageable);

        return ResponseAddressDto.convert(allAddress);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAddressDto> create(@RequestBody @Valid RequestAddressDto requestAddressDto, UriComponentsBuilder uriComponentsBuilder) {

        Address address = requestAddressDto.convert(clientRepository);
        addressRepository.save(address);
        URI uri = uriComponentsBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new ResponseAddressDto(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {
        
        Optional<Address> addressFind = addressRepository.findById(id);

        if(addressFind.isPresent()) {
            return ResponseEntity.ok(new ResponseDetailAddressDto(addressFind.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrors("Endereço não encontrado", HttpStatus.NOT_FOUND.value()));
    }
}
