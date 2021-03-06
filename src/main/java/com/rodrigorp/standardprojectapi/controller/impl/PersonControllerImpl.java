package com.rodrigorp.standardprojectapi.controller.impl;

import com.rodrigorp.standardprojectapi.controller.PersonController;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.dto.PersonUpdateDto;
import com.rodrigorp.standardprojectapi.mapper.PersonMapper;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/person")
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

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        Person person = personService.findById(id);

        return ResponseEntity.ok().body(person);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> personList = personService.findAll();

        return ResponseEntity.ok().body(personList);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Person> update(@RequestBody PersonUpdateDto personUpdateDto, @PathVariable("id") Long id) {
        Person updatedPerson = personService.update(personUpdateDto, id);
        return ResponseEntity.ok().body(updatedPerson);
    }

}