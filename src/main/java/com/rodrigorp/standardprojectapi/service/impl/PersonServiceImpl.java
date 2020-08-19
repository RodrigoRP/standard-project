package com.rodrigorp.standardprojectapi.service.impl;

import com.rodrigorp.standardprojectapi.dao.PersonRepository;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.PersonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Person toModel(PersonNewDto personNewDto) {
        Address address = new Address(personNewDto.getStreet(), personNewDto.getNumber(),
                personNewDto.getCep(), personNewDto.getCity());

        return new Person(null, personNewDto.getFirstName(), personNewDto.getLastName(),
                personNewDto.getPhone(), personNewDto.getEmail(), address);
    }

}