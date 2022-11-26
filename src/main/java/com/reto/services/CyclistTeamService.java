package com.reto.services;

import com.reto.dto.CyclistDTO;
import com.reto.dto.CyclistTeamDTO;
import com.reto.interfaces.ICyclistTeam;
import com.reto.models.Cyclist;
import com.reto.models.CyclistTeam;
import com.reto.repository.CountryRepository;
import com.reto.repository.CyclingTeamRepository;
import com.reto.repository.CyclistRepository;
import com.reto.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class CyclistTeamService implements ICyclistTeam {

    @Autowired
    private CyclingTeamRepository repository;

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Flux<CyclistTeamDTO> findAllCyclingTeams() {
        return repository.findAll().map(AppUtils::cyclistTeamToDto);
    }

    @Override
    public Flux<CyclistDTO> findAllCyclingByTeamCode(String teamCode) {
        return repository.findAll()
                .filter(cyclistTeam -> cyclistTeam.getTeamCode().equals(teamCode))
                .flatMapIterable(CyclistTeam::getCyclists)
                .map(AppUtils::cyclistToDto);
    }

    @Override
    public Mono<CyclistTeamDTO> addCyclistToTeamByTeamIDAndIdCylist(String idTeam, String idCyclist) {
        return repository.findById(idTeam)
                .flatMap(cyclistTeam -> {
                    if (cyclistTeam.getCyclists().size() < 8) {
                        return cyclistRepository.findById(idCyclist)
                                .flatMap(cyclist -> {
                                    cyclistTeam.getCyclists().add(cyclist);
                                    return repository.save(cyclistTeam);
                                });
                    }
                    return Mono.just(cyclistTeam);
                }).map(AppUtils::cyclistTeamToDto);
    }

    @Override
    public Mono<CyclistTeamDTO> saveCyclingTeam(CyclistTeamDTO cyclistTeamDTO, String idCountry) throws ExecutionException, InterruptedException {
        CyclistTeam cyclistTeam = AppUtils.dtoToCyclistTeam(cyclistTeamDTO);
        cyclistTeam.setCountry(countryRepository.findById(idCountry).toFuture().get());

        return repository.save(cyclistTeam).map(AppUtils::cyclistTeamToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(cyclistTeam -> repository.deleteById(cyclistTeam.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistTeamDTO> update(CyclistTeamDTO cyclistTeamDTO, String id) {
        return repository.findById(id)
                .flatMap(cyclistTeam -> {
                    CyclistTeam cyclistTeam1 = AppUtils.dtoToCyclistTeam(cyclistTeamDTO);
                    cyclistTeam1.setId(id);
                    return repository.save(cyclistTeam1);
                })
                .map(AppUtils::cyclistTeamToDto)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistTeamDTO> findCyclingTeamByTeamCode(String teamCode) {
        return repository.findCyclingTeamByTeamCode(teamCode).map(AppUtils::cyclistTeamToDto);
    }


}
