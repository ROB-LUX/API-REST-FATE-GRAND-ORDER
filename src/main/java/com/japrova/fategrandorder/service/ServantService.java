package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface ServantService {

    List<Map<String, String>> findAll();

    Map<String, String> findByName(String name);

    List<Classes> findAllClasses();

    List<LettersTypes> findAllLetters();

}