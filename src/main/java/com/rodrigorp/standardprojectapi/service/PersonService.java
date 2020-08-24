package com.rodrigorp.standardprojectapi.service;

import com.rodrigorp.standardprojectapi.dto.PersonUpdateDto;
import com.rodrigorp.standardprojectapi.model.Person;

public interface PersonService extends GenericService<Person, Long> {

    Person update(PersonUpdateDto personUpdateDto, Long id);

}