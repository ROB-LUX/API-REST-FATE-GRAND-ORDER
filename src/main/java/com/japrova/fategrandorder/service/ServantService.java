package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.entity.Servant;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface ServantService {

    List<Map<String, String>> findAll();

    Servant findByName(String name);

}