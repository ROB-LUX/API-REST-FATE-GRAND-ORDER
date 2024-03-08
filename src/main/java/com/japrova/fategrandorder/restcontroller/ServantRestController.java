package com.japrova.fategrandorder.restcontroller;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.service.ServantServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Map<String, String>> findAll() {

        return servantService.findAll();
    }

    @GetMapping("/servant/{name}")
    public Map<String, String> findName(@PathVariable String name) {
        return servantService.findByName(name);
    }

    public List<List<Object>> findClassesLetters() {

        List<List<Object>> list = new ArrayList<>();

        List<LettersTypes> lettersList = servantService.findAllLetters();
        List<Classes> classesList = servantService.findAllClasses();

        list.add(Collections.singletonList(lettersList));
        list.add(Collections.singletonList(classesList));

        return list;
    }


}