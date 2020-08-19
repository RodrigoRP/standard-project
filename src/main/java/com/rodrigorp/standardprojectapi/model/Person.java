package com.rodrigorp.standardprojectapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    @Embedded // is used to embed a type into another entity.
    private Address address;

}
