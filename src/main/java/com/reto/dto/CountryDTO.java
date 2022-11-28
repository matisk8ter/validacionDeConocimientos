package com.reto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @Size(max = 3)
    private String code;

}
