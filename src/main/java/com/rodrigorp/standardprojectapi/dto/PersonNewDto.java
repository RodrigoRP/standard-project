package com.rodrigorp.standardprojectapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonNewDto {

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String street;

    private String number;

    private String cep;

    private String city;
}
