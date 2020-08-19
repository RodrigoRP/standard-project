package com.rodrigorp.standardprojectapi.dao;

import com.rodrigorp.standardprojectapi.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}