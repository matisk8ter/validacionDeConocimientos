package com.reto.controller;

import com.reto.dto.CyclistDTO;
import com.reto.dto.CyclistTeamDTO;
import com.reto.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/cyclist")
public class CyclistController {

    @Autowired
    private CyclistService service;


    @GetMapping("/{country}")
    public ResponseEntity<Flux<CyclistDTO>> findCyclistByCountry(@PathVariable(value = "country") String country) {
        return ResponseEntity.ok().body(service.findAllCyclistsByCountry(country));
    }

    @GetMapping("/{competitorNumber}")
    public ResponseEntity<Mono<CyclistDTO>> findCyclistByCompetitorNumber(@PathVariable(value = "competitorNumber") String competitorNumber) {
        return ResponseEntity.ok().body(service.findCyclistByCompetitorNumber(competitorNumber));

    }

    @PostMapping("/crear")
    public ResponseEntity<Mono<CyclistDTO>> save(@RequestBody CyclistDTO cyclistDTO) {
        return ResponseEntity.ok().body(service.save(cyclistDTO));
    }

    @GetMapping()
    public ResponseEntity<Flux<CyclistDTO>> findAllCyclist() {
        return ResponseEntity.ok().body(service.findAllCyclists());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return service.delete(id)
                .then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<CyclistDTO>> updateCyclist(@RequestBody CyclistDTO CyclistDTO, @PathVariable(value = "id") String idCyclist) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.update(CyclistDTO, idCyclist));
    }


}
