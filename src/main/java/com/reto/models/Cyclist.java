package com.reto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ciclistas")
public class Cyclist {
    @Id
    private int id;

    private String fullName;

    private String competitorNumber;

    private Country country;

    private CyclistTeam cyclingTeam;
}
