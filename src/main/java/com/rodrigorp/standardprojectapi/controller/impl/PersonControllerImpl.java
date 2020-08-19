package com.rodrigorp.standardprojectapi.controller.impl;

import com.rodrigorp.standardprojectapi.controller.PersonController;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.mapper.PersonMapper;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api/person")
@RestController
public class PersonControllerImpl implements PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonControllerImpl(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody PersonNewDto personNewDto) {
        Person person = personMapper.toModel(personNewDto);
        person = personService.save(person);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}