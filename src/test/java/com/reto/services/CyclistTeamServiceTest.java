package com.reto.services;

import com.reto.models.Country;
import com.reto.models.Cyclist;
import com.reto.models.CyclistTeam;
import com.reto.repository.CyclingTeamRepository;
import com.reto.utils.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.when;

@SpringBootTest
class CyclistTeamServiceTest {

    @MockBean
    CyclingTeamRepository repository;
    @Autowired
    CyclistTeamService service;

    @Test
    void findAllCyclingTeams() {
        /**
         * 	private String id;
         * 	private String name;
         * 	private String teamCode;
         * 	private Country;
         * 	private List<Cyclist> cyclists;
         * */
        List<Cyclist> cyclists = List.of(new Cyclist("1", "mat", "01", "uruguay"),
                new Cyclist("2", "mati", "02", "uruguay"));

        Country country = new Country("1", "uy", "01A");
        CyclistTeam cyclistTeam = new CyclistTeam("1", "equipo1", "0AB", country, cyclists);
        CyclistTeam cyclistTeam2 = new CyclistTeam("2", "equipo2", "0CB", country, cyclists);

        when(repository.findAll())
                .thenReturn(Flux.just(cyclistTeam, cyclistTeam2));

        StepVerifier
                .create(service.findAllCyclingTeams())
                .expectNext(AppUtils.cyclistTeamToDto(cyclistTeam))
                .expectNext(AppUtils.cyclistTeamToDto(cyclistTeam2))
                .expectComplete()
                .verify();


    }

    @Test
    void addCyclistToTeamByTeamIDAndIdCylist() {

    }


    @Test
    void delete() {

    }

    @Test
    void update() {

    }


}