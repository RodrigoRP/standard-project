package com.rodrigorp.standardprojectapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@NoArgsConstructor
@Getter
@Setter
public class PersonUpdateDto {

    private JsonNullable<String> street = JsonNullable.undefined();
    private JsonNullable<String> number = JsonNullable.undefined();
    private JsonNullable<String> cep = JsonNullable.undefined();
    private JsonNullable<String> city = JsonNullable.undefined();
}

