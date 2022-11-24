package com.reto.services;

import com.reto.interfaces.ICountry;
import com.reto.models.Country;
import com.reto.repository.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryService implements ICountry {

    @Autowired
    private CountryRepository repository;

    @Override
    public Flux<Country> findAllCountry() {
        return repository.findAll();
    }

    @Override
    public Mono<Country> save(Country country) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
