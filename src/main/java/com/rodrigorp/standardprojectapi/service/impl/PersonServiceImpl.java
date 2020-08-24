package com.rodrigorp.standardprojectapi.service.impl;

import com.rodrigorp.standardprojectapi.dto.PersonUpdateDto;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.repository.PersonRepository;
import com.rodrigorp.standardprojectapi.service.PersonService;
import com.rodrigorp.standardprojectapi.service.exception.ObjectNotFoundException;
import com.rodrigorp.standardprojectapi.service.utils.JsonNullableUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public Person update(PersonUpdateDto personUpdateDto, Long id) {
        Person returnedPerson = findById(id);

        JsonNullableUtils.changeIfPresent(personUpdateDto.getCep(), returnedPerson.getAddress()::setCep);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getCity(), returnedPerson.getAddress()::setCity);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getNumber(), returnedPerson.getAddress()::setNumber);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getStreet(), returnedPerson.getAddress()::setStreet);

        return repository.save(returnedPerson);
    }

}