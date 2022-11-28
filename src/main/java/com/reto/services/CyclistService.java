package com.reto.services;


import com.reto.dto.CyclistDTO;
import com.reto.interfaces.ICyclist;
import com.reto.models.Cyclist;
import com.reto.repository.CyclingTeamRepository;
import com.reto.repository.CyclistRepository;
import com.reto.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CyclistService implements ICyclist {

    @Autowired
    CyclistRepository repository;

    @Override
    public Flux<CyclistDTO> findAllCyclists() {
        return repository.findAll().map(AppUtils::cyclistToDto);
    }

    @Override
    public Flux<CyclistDTO> findAllCyclistsByCountry(String country) {
        return repository.findAll().filter(cyclist -> cyclist.getCountry().equals(country)).map(AppUtils::cyclistToDto);
    }

    @Override
    public Mono<CyclistDTO> save(CyclistDTO cyclistDTO) {
        Cyclist cyclist = AppUtils.dtoToCyclist(cyclistDTO);
        return repository.save(cyclist).map(AppUtils::cyclistToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(cyclist -> repository.deleteById(cyclist.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistDTO> update(CyclistDTO cyclistDTO, String id) {
        return repository.findById(id)
                .flatMap(cyclist -> {
                    Cyclist cyclist1 = AppUtils.dtoToCyclist(cyclistDTO);
                    cyclist1.setId(id);
                    return repository.save(cyclist1);
                })
                .map(AppUtils::cyclistToDto)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistDTO> findCyclistByCompetitorNumber(String competitorNumber) {
        return repository.findCyclistByCompetitorNumber(competitorNumber).map(AppUtils::cyclistToDto);
    }


}
