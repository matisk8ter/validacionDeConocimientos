package com.reto.dto;

import com.reto.models.Country;
import com.reto.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistTeamDTO {

    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "teamCode Name is required")
    private String teamCode;

    private Country country;

    @Size(max = 8)
    private List<Cyclist> cyclists;
}
