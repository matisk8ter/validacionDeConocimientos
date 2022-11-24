package com.reto.repository;


import com.reto.models.Cyclist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface CyclistRepository extends CrudRepository<Cyclist, String> {
	public Mono<Cyclist> findCyclistByCompetitorNumber(String competitorNumber);
	public Flux<Cyclist> findByCyclingTeamTeamCode(String teamCode);
}
