package com.reto.dto;

import com.reto.models.CyclistTeam;
import com.reto.models.Cyclist;

import java.util.List;

public class CountryDTO {
    private int id;

    private String name;

    private String code;

    private List<CyclistTeam> cyclingTeams;

    private List<Cyclist> cyclists;
}
