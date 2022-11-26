package com.reto.dto;

import com.reto.models.Country;
import com.reto.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistTeamDTO {

    private String id;
    private String name;
    private String teamCode;
    private Country country;
    private List<Cyclist> cyclists;
}
