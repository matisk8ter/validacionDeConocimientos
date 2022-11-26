package com.reto.repository;

import com.reto.models.CyclistTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface CyclingTeamRepository extends ReactiveMongoRepository<CyclistTeam, String> {
	Mono<CyclistTeam> findCyclingTeamByTeamCode(String teamCode);
}
