package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;

import java.util.List;
import java.util.Map;

public interface ServantService {

    List<Map<String, String>> findAllServants();

    Map<String, String> findByName(String name);

    List<Classes> findAllClasses();

    List<LettersTypes> findAllLetters();

}