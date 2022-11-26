package com.reto.interfaces;

import com.reto.dto.CountryDTO;
import com.reto.dto.CyclistDTO;
import com.reto.models.Cyclist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICyclist {
    Flux<CyclistDTO> findAllCyclists();

    Flux<CyclistDTO> findAllCyclistsByCountry(String country);

    Mono<CyclistDTO> save(CyclistDTO cyclistDTO);

    Mono<Void> delete(String id);

    Mono<CyclistDTO> update(CyclistDTO cyclistDTO, String id);

    Mono<CyclistDTO> findCyclistByCompetitorNumber(String competitorNumber);

}
