package com.reto.interfaces;

import com.reto.dto.CountryDTO;
import com.reto.models.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICountry {
    Flux<CountryDTO> findAllCountry();
    Mono<CountryDTO> save(Country country);
   Mono<Void> delete(String id);

}
