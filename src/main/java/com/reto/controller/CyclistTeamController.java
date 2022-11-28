package com.reto.controller;

import com.reto.dto.CyclistDTO;
import com.reto.dto.CyclistTeamDTO;
import com.reto.services.CyclistTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/team")
public class CyclistTeamController {

    @Autowired
    private CyclistTeamService service;

    @GetMapping("/{team_code}")
    public ResponseEntity<Mono<CyclistTeamDTO>> findCyclingTeamByCode(@PathVariable(value = "team_code") String teamCode) {
        return ResponseEntity.ok().body(service.findCyclingTeamByTeamCode(teamCode));
    }

    @PostMapping("/crear/{id}")
    public ResponseEntity<Mono<CyclistTeamDTO>> save(@RequestBody CyclistTeamDTO cyclistTeamDTO, @PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(service.saveCyclingTeam(cyclistTeamDTO, id));
    }

    @GetMapping()
    public ResponseEntity<Flux<CyclistTeamDTO>> findAllCyclistTeam() {
        return ResponseEntity.ok().body(service.findAllCyclingTeams());
    }

    @GetMapping("/{teamCode}")
    public ResponseEntity<Flux<CyclistDTO>> findAllCyclingByTeamCode(@PathVariable("teamCode") String teamCode) {
        return ResponseEntity.ok().body(service.findAllCyclingByTeamCode(teamCode));
    }

    @PutMapping("/addCyclistToTeam/{id}/{idCyclist}")
    public Mono<CyclistTeamDTO> addCyclistToTeam(@PathVariable("id") String id, @PathVariable("idCyclist") String idCyclist) {
        return service.addCyclistToTeamByTeamIDAndIdCylist(id, idCyclist);
    }
    @PutMapping("/update/{id}")
    public Mono<CyclistTeamDTO> update(@RequestBody CyclistTeamDTO cyclistTeamDTO, @PathVariable("id") String id) {
        return service.update(cyclistTeamDTO, id);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return service.delete(id)
                .then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
