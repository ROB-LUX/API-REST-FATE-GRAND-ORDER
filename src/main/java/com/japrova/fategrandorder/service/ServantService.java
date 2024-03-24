package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.CardTypes;
import com.japrova.fategrandorder.entity.Servant;

import java.util.List;

public interface ServantService {

    List<ServantDto> findAllServants();

    ServantDto findByName(String name);

    List<Classes> findAllClasses();

    List<CardTypes> findAllLetters();

    ServantDto saveServant(ServantDto servant);

    ServantDto updateServant(ServantDto servant);

    void deleteServant(int idServant);

}