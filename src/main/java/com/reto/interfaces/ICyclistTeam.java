package com.reto.interfaces;

import com.reto.dto.CyclistDTO;
import com.reto.dto.CyclistTeamDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface ICyclistTeam {
    Flux<CyclistTeamDTO> findAllCyclingTeams();
    Flux<CyclistDTO> findAllCyclingByTeamCode(String teamCode);
    Mono<CyclistTeamDTO> addCyclistToTeamByTeamIDAndIdCylist(String idTeam, String idCyclist);
    Mono<CyclistTeamDTO> saveCyclingTeam(CyclistTeamDTO cyclistTeamDTO, String idCountry) throws ExecutionException, InterruptedException;
    Mono<Void> delete(String id);

    Mono<CyclistTeamDTO> update(CyclistTeamDTO cyclistTeamDTO, String id);

    Mono<CyclistTeamDTO> findCyclingTeamByTeamCode(String teamCode);

}
