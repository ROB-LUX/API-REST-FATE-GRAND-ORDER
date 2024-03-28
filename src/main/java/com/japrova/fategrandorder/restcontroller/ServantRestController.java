package com.japrova.fategrandorder.restcontroller;

import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ClassDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.service.IObtainingData;
import com.japrova.fategrandorder.service.ITransactionalOperations;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ServantRestController {

    private final ITransactionalOperations servantService;

    private final IObtainingData obtainingData;
    public ServantRestController(ITransactionalOperations operations, IObtainingData obtainingData) {
        this.servantService = operations;
        this.obtainingData = obtainingData;
    }

    @GetMapping("/servants")
    public List<ServantDto> findAllServants() {

        return obtainingData.findAllServants();
    }

    @GetMapping("/servant/{name}")
    public ServantDto findServantByName(@PathVariable String name) {
        return obtainingData.findServantByName(name);
    }

    @GetMapping("/findClasses")
    public Set<ClassDto> findClasses() {

        return obtainingData.findClasses();
    }

    @GetMapping("/findCards")
    public Set<CardTypeDto> findCardsType() {
        return obtainingData.findCardsType();
    }

    @PostMapping("/save-servant")
    public ServantDto saveServant(@RequestBody ServantDto servant) {

        return servantService.saveServant(servant);
    }

    @PutMapping("/updateServant")
    public ServantDto updateServant(@RequestBody ServantDto servant) {

        return servantService.updateServant(servant);
    }

    @DeleteMapping("/delete-servant/{idServant}")
    public void deleteServant(@PathVariable int idServant) {

        servantService.deleteServant(idServant);
    }
}