package com.rodrigorp.standardprojectapi.controller;

import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Person API")
public interface PersonController {

    @ApiOperation("Add new data")
    ResponseEntity<Person> save(@RequestBody PersonNewDto personNewDto);

    @ApiOperation("Find by Id")
    ResponseEntity<Person> findById(Long id);
/*
    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<Person> list();

    @ApiOperation("Pagination request")
    public Page<Person> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public Person update(@RequestBody Person dto, @PathVariable("id") Long id);*/
}