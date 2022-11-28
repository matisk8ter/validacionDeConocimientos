package com.reto.services;

import com.reto.models.Cyclist;
import com.reto.repository.CyclistRepository;
import com.reto.utils.AppUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CyclistServiceTest {

    @Autowired
    private CyclistService service;

    @MockBean
    private CyclistRepository repository;

    @Test
    void findAllCyclists() {

        Cyclist cyclist = new Cyclist("1", "mat", "01", "uruguay");
        Cyclist cyclist2 = new Cyclist("2", "mati", "02", "uruguay");

        when(repository.findAll())
                .thenReturn(Flux.just(cyclist, cyclist2));

        StepVerifier
                .create(service.findAllCyclists())
                .expectNext(AppUtils.cyclistToDto(cyclist))
                .expectNext(AppUtils.cyclistToDto(cyclist2))
                .expectComplete()
                .verify();

    }

    @Test
    void save() {

        Cyclist cyclist = new Cyclist("1", "mat", "01", "uruguay");


        when(repository.save(cyclist))
                .thenReturn(Mono.just(cyclist));

        StepVerifier
                .create(Mono.just(AppUtils.cyclistToDto(cyclist)))
                .expectNext(AppUtils.cyclistToDto(cyclist))
                .expectComplete()
                .verify();


    }

    @Test
    void delete() {

        Cyclist cyclist = new Cyclist("1", "mat", "01", "uruguay");

        when(repository.findById("1")).thenReturn(Mono.just(cyclist));
        when(repository.deleteById("1")).thenReturn(Mono.empty());

        StepVerifier
                .create(service.delete(cyclist.getId()))
                .expectNext()
                .expectComplete()
                .verify();
    }

    @Test
    void update() {

        Cyclist cyclist = new Cyclist();
        cyclist.setCompetitorNumber("1");
        cyclist.setCountry("Uruguay");
        cyclist.setFullName("Matias");

        Cyclist cyclistModified = new Cyclist();
        cyclistModified.setCompetitorNumber("1");
        cyclistModified.setCountry("Uruguay");
        cyclistModified.setFullName("Matias Souza");


        when(repository.save(cyclist))
                .thenReturn(Mono.just(cyclist));

        when(repository.findById(cyclist.getId()))
                .thenReturn(Mono.just(cyclist));

        when(repository.save(cyclistModified))
                .thenReturn(Mono.just(cyclistModified));

        StepVerifier
                .create(service.update(AppUtils.cyclistToDto(cyclistModified), cyclist.getId()))
                .expectNextMatches(cyclistDto  ->
                        cyclistDto.getFullName().equals("Matias Souza") &&
                                cyclistDto.getCompetitorNumber().equals("1") &&
                                cyclistDto.getCountry().equals("Uruguay")
                )
                .expectComplete()
                .verify();

    }


}