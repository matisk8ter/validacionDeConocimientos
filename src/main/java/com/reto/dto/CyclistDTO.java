package com.reto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String id;

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "competitor number is required")
    private String competitorNumber;

    @NotBlank(message = "competitor number is required")
    private String country;
}
