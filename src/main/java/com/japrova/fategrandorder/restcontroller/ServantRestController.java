package com.japrova.fategrandorder.restcontroller;

import com.japrova.fategrandorder.entity.*;
import com.japrova.fategrandorder.service.ServantServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServantRestController {

    private final ServantServiceImpl servantService;

    public ServantRestController(ServantServiceImpl servantService) {
        this.servantService = servantService;
    }

    @GetMapping("/servants")
    public /*List<Map<String, String>>*/ String findAllServants() {

        /*return servantService.findAllServants();*/
        return "Hola mundo";
    }

    @GetMapping("/servant/{name}")
    public Map<String, String> findServantByName(@PathVariable String name) {
        return servantService.findByName(name);
    }

    @GetMapping("/findAll")
    public List<List<Object>> findClassesLetters() {

        List<List<Object>> list = new ArrayList<>();

        List<LettersTypes> lettersList = servantService.findAllLetters();
        List<Classes> classesList = servantService.findAllClasses();

        list.add(Collections.singletonList(lettersList));
        list.add(Collections.singletonList(classesList));

        return list;
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

    @GetMapping("/letters")
    public List<LettersTypes> lettersTypes() {


        return servantService.findAllLetters();
    }
}