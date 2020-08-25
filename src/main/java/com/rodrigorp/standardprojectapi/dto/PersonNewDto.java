package com.rodrigorp.standardprojectapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonNewDto {

  //  @ApiModelProperty(example = "MariaXX", required = true)
    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String addressStreet;

    private String addressNumber;

    private String addressCep;

    private String addressCity;
}
