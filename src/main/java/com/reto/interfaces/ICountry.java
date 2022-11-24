package com.reto.interfaces;

import com.reto.models.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICountry {
    Flux<Country> findAllCountry();
    Mono<Country> save(Country country);
    void delete(String id);

}
