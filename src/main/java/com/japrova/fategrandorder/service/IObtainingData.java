package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.CardTypes;
import com.japrova.fategrandorder.entity.Classes;

import java.util.List;

public interface IObtainingData {

    List<ServantDto> findAllServants();

    ServantDto findServantByName(String name);
}
