package com.rodrigorp.standardprojectapi.dao;

import com.rodrigorp.standardprojectapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}