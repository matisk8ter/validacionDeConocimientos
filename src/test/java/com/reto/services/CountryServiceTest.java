package com.reto.services;

import com.reto.models.Country;
import com.reto.repository.CountryRepository;
import com.reto.utils.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
class CountryServiceTest {

    @MockBean
    CountryRepository repository;

    @Autowired
    CountryService service;

    @Test
    void findAllCountry() {

        Country country = new Country("1", "uy", "01A");
        Country country2 = new Country("2", "fr", "02A");

        when(repository.findAll())
                .thenReturn(Flux.just(country, country2));

        StepVerifier
                .create(service.findAllCountry())
                .expectNext(AppUtils.countryToDto(country))
                .expectNext(AppUtils.countryToDto(country2))
                .expectComplete()
                .verify();

    }

    @Test
    void save() {

        Country country = new Country("1", "uy", "01A");

        when(repository.save(country))
                .thenReturn(Mono.just(country));

        StepVerifier
                .create(service.save(AppUtils.countryToDto(country)))
                .expectNextMatches(countryDTO ->
                        countryDTO.getId().equals("1") &&
                                countryDTO.getCode().equals("01A") &&
                                countryDTO.getName().equals("uy")
                )
                .expectComplete()
                .verify();
    }

    @Test
    void delete() {

        Country country = new Country("1", "uy", "01A");

        when(repository.findById("1")).thenReturn(Mono.just(country));
        when(repository.deleteById("1")).thenReturn(Mono.empty());

        StepVerifier
                .create(service.delete(country.getId()))
                .expectNext()
                .expectComplete()
                .verify();
    }

    @Test
    void update() {

        Country country = new Country();
        country.setId("1");
        country.setCode("1");
        country.setName("Uru why");

        Country countryNew = new Country();
        countryNew.setId("1");
        countryNew.setCode("1");
        countryNew.setName("Uruguay");

        when(repository.save(country))
                .thenReturn(Mono.just(country));

        when(repository.findById(country.getId()))
                .thenReturn(Mono.just(country));

        when(repository.save(countryNew))
                .thenReturn(Mono.just(countryNew));


        StepVerifier
                .create(service.update(AppUtils.countryToDto(countryNew), country.getId()))
                .expectNextMatches(countryDTO ->
                        countryDTO.getId().equals("1") &&
                                countryDTO.getName().equals("Uruguay") &&
                                countryDTO.getCode().equals("1")
                ).expectComplete()
                .verify();
    }


}