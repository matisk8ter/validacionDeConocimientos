package com.reto.utils;

import com.reto.dto.CountryDTO;
import com.reto.dto.CyclistDTO;
import com.reto.dto.CyclistTeamDTO;
import com.reto.models.Country;
import com.reto.models.CyclistTeam;
import com.reto.models.Cyclist;
import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static CountryDTO countryToDto(Country country){
        CountryDTO countryDTO = new CountryDTO();
        BeanUtils.copyProperties(country, countryDTO);
        return countryDTO;
    }
    public static CyclistDTO cyclistToDto(Cyclist cyclist){
        CyclistDTO cyclistDTO = new CyclistDTO();
        BeanUtils.copyProperties(cyclist, cyclistDTO);
        return cyclistDTO;
    }
    public static CyclistTeamDTO cyclistTeamToDto(CyclistTeam cyclistTeam){
        CyclistTeamDTO cyclistTeamDTO = new CyclistTeamDTO();
        BeanUtils.copyProperties(cyclistTeam, cyclistTeamDTO);
        return cyclistTeamDTO;
    }


    //--------DTO TO SCHEMA---------

    public static Country dtoToCountry(CountryDTO countryDTO){
        Country country = new Country();
        BeanUtils.copyProperties(countryDTO, country);
        return country;
    }
    public static Cyclist dtoToCyclist(CountryDTO cyclistDto){
        Cyclist cyclist = new Cyclist();
        BeanUtils.copyProperties(cyclistDto, cyclist);
        return cyclist;
    }
    public static CyclistTeam dtoToCyclistTeam(CyclistTeamDTO cyclistTeamDTO){
        CyclistTeam cyclistTeam = new CyclistTeam();
        BeanUtils.copyProperties(cyclistTeamDTO, cyclistTeam);
        return cyclistTeam;
    }

}
