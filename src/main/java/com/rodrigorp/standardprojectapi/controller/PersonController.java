package com.rodrigorp.standardprojectapi.controller;

import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.dto.PersonUpdateDto;
import com.rodrigorp.standardprojectapi.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Person API")
public interface PersonController {

    @ApiOperation("Add new data")
    ResponseEntity<Person> save(@RequestBody PersonNewDto personNewDto);

    @ApiOperation("Find by Id")
    ResponseEntity<Person> findById(Long id);

    @ApiOperation("Find all data")
    ResponseEntity<List<Person>> findAll();

    @ApiOperation("Delete based on primary key")
    ResponseEntity<Void> delete(Long id);

    @ApiOperation("Update one data")
    ResponseEntity<Person> update(PersonUpdateDto personUpdateDto, Long id);
}