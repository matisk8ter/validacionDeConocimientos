package com.reto.controller;

import com.reto.dto.CountryDTO;
import com.reto.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryService service;

    @GetMapping("/getAll")
    public ResponseEntity<Flux<CountryDTO>> findAllCountry() {
        return ResponseEntity.ok().body(service.findAllCountry());
    }
    @GetMapping("/{code}")
    public Mono<CountryDTO> findCountryByCode(@PathVariable("code") String code) {
        return service.countryByCode(code);
    }

    @PostMapping("/crear")
    public ResponseEntity<Mono<CountryDTO>> save(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok().body(service.save(countryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<CountryDTO>> updateCountry(@RequestBody CountryDTO countryDTO, @PathVariable(value = "id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.update(countryDTO, id));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return service.delete(id)
                .then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
