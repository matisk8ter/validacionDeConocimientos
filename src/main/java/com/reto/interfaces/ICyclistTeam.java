package com.reto.interfaces;

import com.reto.models.CyclistTeam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICyclistTeam {
    Flux<CyclistTeam> findAllCyclingTeams();
    Mono<CyclistTeam> saveCyclingTeam(CyclistTeam cyclistTeam);
}
