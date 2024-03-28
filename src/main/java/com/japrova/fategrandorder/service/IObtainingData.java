package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ClassDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.CardTypes;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.enums.CardTypeEnum;
import com.japrova.fategrandorder.entity.enums.ClassesEnum;

import java.util.List;
import java.util.Set;

public interface IObtainingData {

    List<ServantDto> findAllServants();

    ServantDto findServantByName(String name);

    Set<ClassDto> findClasses();

    Set<CardTypeDto> findCardsType();
}
