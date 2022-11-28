package com.reto.services;

import com.reto.dto.CountryDTO;
import com.reto.interfaces.ICountry;
import com.reto.models.Country;
import com.reto.repository.CountryRepository;
import com.reto.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService implements ICountry {

    @Autowired
     CountryRepository repository;

    @Override
    public Flux<CountryDTO> findAllCountry() {
        return repository.findAll()
                .map(AppUtils::countryToDto);
    }

    @Override
    public Mono<CountryDTO> save(CountryDTO countryDTO) {
        Country country = AppUtils.dtoToCountry(countryDTO);
        return repository.save(country)
                .map(AppUtils::countryToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(country -> repository.deleteById(country.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CountryDTO> update(CountryDTO countryDTO, String id) {
        return repository.findById(id)
                .flatMap(country1 -> {
                    Country country2 = AppUtils.dtoToCountry(countryDTO);
                    country2.setId(id);
                    return repository.save(country2);
                })
                .map(AppUtils::countryToDto)
                .switchIfEmpty(Mono.empty());
    }


    @Override
    public Mono<CountryDTO> countryByCode(String code) {
        return repository.findCountryByCode(code).map(AppUtils::countryToDto);
    }
}
