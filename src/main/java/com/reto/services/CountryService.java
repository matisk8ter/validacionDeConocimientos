package com.reto.services;

import com.reto.dto.CountryDTO;
import com.reto.interfaces.ICountry;
import com.reto.models.Country;
import com.reto.repository.CountryRepository;
import com.reto.utils.AppUtils;
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
    public Flux<CountryDTO> findAllCountry() {
        return repository.findAll().map(AppUtils::countryToDto);
    }

    @Override
    public Mono<CountryDTO> save(Country country) {
        return repository.save(country).thenReturn(AppUtils.countryToDto(country));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
