package com.japrova.fategrandorder.restcontroller;

import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.*;
import com.japrova.fategrandorder.service.ServantServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ServantRestController {

    private final ServantServiceImpl servantService;

    public ServantRestController(ServantServiceImpl servantService) {
        this.servantService = servantService;
    }

    @GetMapping("/servants")
    public List<ServantDto> findAllServants() {

        return servantService.findAllServants();
    }

    @GetMapping("/servant/{name}")
    public ServantDto findServantByName(@PathVariable String name) {
        return servantService.findByName(name);
    }

    @GetMapping("/findAll")
    public List<Object> findClassesLetters() {

        return Arrays.asList(servantService.findAllLetters(), servantService.findAllClasses());
    }

    @PostMapping("/saveServant")
    public ServantDto saveServant(@RequestBody ServantDto servant) {

        return servantService.saveServant(servant);
    }

    @PutMapping("/updateServant")
    public ServantDto updateServant(@RequestBody ServantDto servant) {

        return servantService.updateServant(servant);
    }

    @DeleteMapping("/deleteServant/{idServant}")
    public void deleteServant(@PathVariable int idServant) {

        servantService.deleteServant(idServant);
    }
}