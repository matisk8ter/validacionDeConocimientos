package com.reto.interfaces;

import com.reto.dto.CountryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICountry {
    Flux<CountryDTO> findAllCountry();

    Mono<CountryDTO> save(CountryDTO countryDTO);

    Mono<Void> delete(String id);

    Mono<CountryDTO> update(CountryDTO countryDTO, String id);

    Mono<CountryDTO> countryByCode(String code);
}
