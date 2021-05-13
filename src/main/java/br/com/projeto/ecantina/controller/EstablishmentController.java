package br.com.projeto.ecantina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.ecantina.dto.response.ResponseEstablishmentDto;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

@RestController
@RequestMapping("/establishment")
@CrossOrigin
public class EstablishmentController {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @GetMapping
    public Page<ResponseEstablishmentDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable) {

        Page<Establishment> allEstablishments = establishmentRepository.findAll(pageable);

        return ResponseEstablishmentDto.convert(allEstablishments);
    }

    
}
