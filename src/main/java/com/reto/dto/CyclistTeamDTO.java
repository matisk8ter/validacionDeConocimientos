package com.reto.dto;

import com.reto.models.Country;
import com.reto.models.Cyclist;

import java.util.List;

public class CyclistTeamDTO {

    private int id;

    private String name;

    private String teamCode;

    private Country country;

    private List<Cyclist> cyclists;
}
