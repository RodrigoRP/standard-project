package com.rodrigorp.standardprojectapi.service.impl;

import com.rodrigorp.standardprojectapi.dao.PersonRepository;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.PersonService;
import com.rodrigorp.standardprojectapi.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person entity) {
        return repository.save(entity);
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> person = repository.findById(id);
        return person.orElseThrow(() -> new ObjectNotFoundException("" +
                "Person not found! Id: " + id));
    }

    public Person toModel(PersonNewDto personNewDto) {
        Address address = new Address(personNewDto.getStreet(), personNewDto.getNumber(),
                personNewDto.getCep(), personNewDto.getCity());

        return new Person(null, personNewDto.getFirstName(), personNewDto.getLastName(),
                personNewDto.getPhone(), personNewDto.getEmail(), address);
    }

}