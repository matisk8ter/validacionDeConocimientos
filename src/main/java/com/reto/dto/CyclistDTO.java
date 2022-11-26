package com.reto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String id;
    private String fullName;
    private String competitorNumber;
    private String country;
}
