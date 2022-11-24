package com.reto.repository;

import com.reto.models.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface CountryRepository extends ReactiveMongoRepository<Country, String> {
	Mono<Country> findCountryByCode(String code);
}
