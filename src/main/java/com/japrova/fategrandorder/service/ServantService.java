package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.entity.Servant;

import java.util.List;

public interface ServantService {

    List<Servant> findAll();

    Servant findByName(String name);

}