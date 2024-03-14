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

        return Arrays.asList(servantService.findAllClasses(),
                servantService.findAllLetters());

    }

    @PostMapping("/persistServant")
    public String persistServant(@RequestBody Servant servant) {

        String message = "Message: ";

        boolean validation = servantService.persistServant(servant);

        if (validation) {
            return message.concat("Servant Kept");
        } else {
            return message.concat("Unguarded Servant");
        }
    }

    @PutMapping("/servant-update")
    public Servant updateServant(@RequestBody Servant servant) {

        Servant updateServant = servantService.updateServant(servant);

        return updateServant;
    }
}