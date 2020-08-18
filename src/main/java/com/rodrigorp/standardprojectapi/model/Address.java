package com.rodrigorp.standardprojectapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable //annotation to declare that a class will be embedded by other entities.
public class Address {

    private String street;

    private String number;

    private String cep;

    private String city;
}
