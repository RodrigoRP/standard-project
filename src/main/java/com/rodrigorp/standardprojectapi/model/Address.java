package com.rodrigorp.standardprojectapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable //annotation to declare that a class will be embedded by other entities.
public class Address implements Serializable {

    private String street;

    private String number;

    private String cep;

    private String city;
}
